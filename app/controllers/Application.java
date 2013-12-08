package controllers;

import java.io.File;

import play.i18n.Messages;
import play.Logger; 
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import services.PlaylistService;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

	@BodyParser.Of(value = BodyParser.MultipartFormData.class, maxLength = 25 * 1024 * 1024)
	public static Result upload() {
		MultipartFormData body = request().body().asMultipartFormData();
		if (request().body().isMaxSizeExceeded()) {
			flash("error", Messages.get("upload.error.maxsize"));
			return redirect(routes.Application.index());
		}
		FilePart playlist = body.getFile("playlist");
		if (playlist != null) {
			String contentType = playlist.getContentType();
			if (!contentType.equals("text/xml")) {
				flash("error", Messages.get("upload.error.contenttype"));
				return redirect(routes.Application.index());
			}
			else {
				try {
					File file = playlist.getFile();
					PlaylistService.parseXMLFile(file);
					return redirect(routes.Application.download());
				}
				catch (Exception ex) {
					flash("error", Messages.get("upload.error.parse"));
					if (Logger.isDebugEnabled())
						Logger.debug("File: " + playlist.getFilename(), ex);
					return redirect(routes.Application.index());
				}
			}
		} else {
			flash("error", Messages.get("upload.error.missing"));
			return redirect(routes.Application.index());
		}
	}

    public static Result download() {
        return ok(download.render());
    }

    public static Result about() {
        return ok(about.render("All about the site"));
    }

}
