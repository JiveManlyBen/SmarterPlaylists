package controllers;

import java.io.File;

import play.*;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Smarter Playlists", "Content"));
    }

	@BodyParser.Of(value = BodyParser.MultipartFormData.class, maxLength = 25 * 1024 * 1024)
	public static Result upload() {
		MultipartFormData body = request().body().asMultipartFormData();
		if (request().body().isMaxSizeExceeded()) {
			flash("error", "File exceeded maximum size");
			return redirect(routes.Application.index());
		}
		FilePart playlist = body.getFile("playlist");
		if (playlist != null) {
			String fileName = playlist.getFilename();
			String contentType = playlist.getContentType();
			if (!contentType.equals("text/xml")) {
				flash("error", "Incorrect content type");
				return redirect(routes.Application.index());
			}
			else {
				File file = playlist.getFile();
				return ok("Uploaded file: " + fileName);
			}
		} else {
			flash("error", "Missing file");
			return redirect(routes.Application.index());
		}
	}

    public static Result about() {
        return ok(about.render("About", "All about the site"));
    }

}