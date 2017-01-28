package controllers;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import domain.PlaylistLimit;

import play.Logger;
import play.i18n.Lang;
import play.i18n.Messages;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import services.FileService;
import views.html.about;
import views.html.compare;
import views.html.csv;
import views.html.download;
import views.html.index;
import enums.TrackFilterType;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(TrackFilterType.getCodes(), PlaylistLimit.countSuffix, 
        		PlaylistLimit.hoursSuffix, PlaylistLimit.minutesSuffix));
    }

	@BodyParser.Of(value = BodyParser.MultipartFormData.class, maxLength = 25 * 1024 * 1024)
	public static Result upload() {
		MultipartFormData body = request().body().asMultipartFormData();
		if (request().body().isMaxSizeExceeded()) {
			flash("error", Messages.get("upload.error.maxsize"));
			return redirect(routes.Application.index());
		}
		String operation = body.asFormUrlEncoded().get("operation")[0];
		if (operation.equals("singleplaylist")) {
			Set<String> codes = new HashSet<String>(TrackFilterType.getCodes());
			codes.retainAll(body.asFormUrlEncoded().keySet());
			if (codes.size() == 0) {
				flash("error", Messages.get("upload.error.nooption"));
				return redirect(routes.Application.index());
			}
			FilePart libraryFilePart = body.getFile("library");
			if (libraryFilePart != null) {
				String contentType = libraryFilePart.getContentType();
				if (!contentType.equals("text/xml")) {
					flash("error", Messages.get("upload.error.contenttype"));
					return redirect(routes.Application.index());
				}
				else {
					try {
						File file = libraryFilePart.getFile();
						String uuid = session("uuid");
						if (uuid == null) {
						    uuid = java.util.UUID.randomUUID().toString();
						    session("uuid", uuid);
						}
						Map<String, PlaylistLimit> codeMap = new LinkedHashMap<String, PlaylistLimit>();
						for (String code : codes) {
							String count = body.asFormUrlEncoded().get(code + PlaylistLimit.countSuffix)[0];
							String hours = body.asFormUrlEncoded().get(code + PlaylistLimit.hoursSuffix)[0];
							String minutes = body.asFormUrlEncoded().get(code + PlaylistLimit.minutesSuffix)[0];
							PlaylistLimit limits = new PlaylistLimit(count, hours, minutes);
							codeMap.put(code, limits);
						}
						FileService.deleteTempPlaylistFiles(uuid);
						FileService.createTempXmlPlaylistFiles(file, codeMap, uuid);
						return redirect(routes.Application.downloads());
					}
					catch (Exception ex) {
						Logger.error("Error when parsing XML file", ex);
						flash("error", Messages.get("upload.error.parse"));
						return redirect(routes.Application.index());
					}
				}
			} else {
				flash("error", Messages.get("upload.error.missing"));
				return redirect(routes.Application.index());
			}
		} else if (operation.equals("converttocsv")) {
			FilePart libraryFilePart = body.getFile("library");
			if (libraryFilePart != null) {
				String contentType = libraryFilePart.getContentType();
				if (!contentType.equals("text/xml")) {
					flash("error", Messages.get("upload.error.contenttype"));
					return redirect(routes.Application.csv());
				}
				else {
					try {
						File file = libraryFilePart.getFile();
						String uuid = session("uuid");
						if (uuid == null) {
						    uuid = java.util.UUID.randomUUID().toString();
						    session("uuid", uuid);
						}
						FileService.deleteTempPlaylistFiles(uuid);
						FileService.createTempCsvPlaylistFiles(file, uuid);
						return redirect(routes.Application.downloads());
					}
					catch (Exception ex) {
						Logger.error("Error when parsing XML file", ex);
						flash("error", Messages.get("upload.error.parse"));
						return redirect(routes.Application.csv());
					}
				}
			} else {
				flash("error", Messages.get("upload.error.missing"));
				return redirect(routes.Application.csv());
			}
		} else if (operation.equals("comparelibraries")) {
			FilePart firstLibraryFilePart = body.getFile("firstLibrary");
			FilePart secondLibraryFilePart = body.getFile("secondLibrary");
			if (firstLibraryFilePart != null && secondLibraryFilePart != null) {
				String firstContentType = firstLibraryFilePart.getContentType();
				String secondContentType = secondLibraryFilePart.getContentType();
				if (!firstContentType.equals("text/xml") || !secondContentType.equals("text/xml")) {
					flash("error", Messages.get("upload.error.contenttype"));
					return redirect(routes.Application.compare());
				}
				else {
					try {
						File firstFile = firstLibraryFilePart.getFile();
						File secondFile = secondLibraryFilePart.getFile();
						String uuid = session("uuid");
						if (uuid == null) {
						    uuid = java.util.UUID.randomUUID().toString();
						    session("uuid", uuid);
						}
						FileService.deleteTempPlaylistFiles(uuid);
						FileService.createTempXmlPlaylistFilesForCompare(firstFile, secondFile, uuid);
						return redirect(routes.Application.downloads());
					}
					catch (Exception ex) {
						Logger.error("Error when parsing XML file", ex);
						flash("error", Messages.get("upload.error.parse"));
						return redirect(routes.Application.compare());
					}
				}
			} else {
				flash("error", Messages.get("upload.error.missing"));
				return redirect(routes.Application.compare());
			}
		} else {
			flash("error", Messages.get("upload.error.invalid"));
			return redirect(routes.Application.index());
		}
	}

    public static Result downloads() {
    	return ok(download.render(session("uuid"), FileService.getTempPlaylistFiles(session("uuid"))));
    }

    public static Result download(String uuid, String file) {
    	try {
    		if (!StringUtils.isEmpty(uuid) && uuid.equals(session("uuid"))) {
    			String fileExtention = "." + FilenameUtils.getExtension(file);
    			File download = null;
    			if (fileExtention.equals(FileService.CSV_EXTENSION)) {
    				download = FileService.getTempCsvPlaylistFile(uuid, file);
    			} else if (fileExtention.equals(FileService.M3U_EXTENSION)) {
    				download = FileService.getTempM3uPlaylistFile(uuid, file);
    			} else if (fileExtention.equals(FileService.XML_EXTENSION)) {
    				download = FileService.getTempXmlPlaylistFile(uuid, file);
    			} else {
    				throw new Exception("Invalid file extension (file: '" + file + "', extension: '" + fileExtention + "')");
    			}
    			String fileName = null;
				if (download.getName().replace(fileExtention, "").equals("mostoftenplayed")) {
					fileName = Messages.get("filter.label.mostoftenplayed") + fileExtention;
				} else if (download.getName().replace(fileExtention, "").equals("library")) {
					fileName = Messages.get("filter.label.library") + fileExtention;
				} else if (download.getName().replace(fileExtention, "").equals("firstLibrarySongs")) {
					fileName = Messages.get("filter.label.firstlibraryplaylist") + fileExtention;
				} else if (download.getName().replace(fileExtention, "").equals("secondLibrarySongs")) {
					fileName = Messages.get("filter.label.secondlibraryplaylist") + fileExtention;
				}
    			response().setHeader("Content-Disposition", "attachment; filename=\"" + fileName +"\"");
	    		return ok(download);
	    	}
	    }
    	catch (Exception ex) {
    		Logger.error("Error when downloading playlist file", ex);
    	}
		flash("error", Messages.get("download.error.notfound"));
		return redirect(routes.Application.downloads());
    }
    
    public static Result compare() {
    	return ok(compare.render());
    }

    public static Result csv() {
        return ok(csv.render());
    }

    public static Result about() {
        return ok(about.render());
    }

    public static Result language() {
    	String name = request().body().asFormUrlEncoded().get("name")[0];
    	if (Lang.availables().contains(Lang.forCode(name))) {
    		changeLang(name);
    		return ok();
    	}
    	else
    		return status(BAD_REQUEST);
    }

}
