package controllers;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import play.i18n.Lang;
import play.i18n.Messages;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import services.FileService;
import views.html.about;
import views.html.download;
import views.html.index;
import enums.TrackFilterType;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(TrackFilterType.getCodes()));
    }

	@BodyParser.Of(value = BodyParser.MultipartFormData.class, maxLength = 25 * 1024 * 1024)
	public static Result upload() {
		MultipartFormData body = request().body().asMultipartFormData();
		if (request().body().isMaxSizeExceeded()) {
			flash("error", Messages.get("upload.error.maxsize"));
			return redirect(routes.Application.index());
		}
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
					Map<String, String> codeMap = new LinkedHashMap<String, String>();
					for (String code : codes) {
						codeMap.put(code, body.asFormUrlEncoded().get(code + "_limit")[0]);
					}
					FileService.deleteTempPlaylistFiles(uuid);
					FileService.createTempPlaylistFiles(file, codeMap, uuid);
					return redirect(routes.Application.downloads());
				}
				catch (Exception ex) {
					flash("error", Messages.get("upload.error.parse"));
					return redirect(routes.Application.index());
				}
			}
		} else {
			flash("error", Messages.get("upload.error.missing"));
			return redirect(routes.Application.index());
		}
	}

    public static Result downloads() {
    	return ok(download.render(session("uuid"), FileService.getTempPlaylistFiles(session("uuid"))));
    }

    public static Result download(String uuid, String file) {
    	try {
    		if (!StringUtils.isEmpty(uuid) && uuid.equals(session("uuid"))) {
	    		return ok(FileService.getTempPlaylistFile(uuid, file));
	    	}
	    }
    	catch (Exception ex) {
    		ex.printStackTrace();
    	}
		flash("error", Messages.get("download.error.notfound"));
		return redirect(routes.Application.downloads());
    }

    public static Result about() {
        return ok(about.render("All about the site"));
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
