package controllers;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.XMLConstants;

import org.w3c.dom.Document;

import play.*;
import play.api.i18n.Lang;
import play.i18n.Messages;
import play.Logger;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Content"));
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
			String fileName = playlist.getFilename();
			String contentType = playlist.getContentType();
			if (!contentType.equals("text/xml")) {
				flash("error", Messages.get("upload.error.contenttype"));
				return redirect(routes.Application.index());
			}
			else {
				File file = playlist.getFile();
				DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
				domFactory.setValidating(true);
				try {
					DocumentBuilder builder = domFactory.newDocumentBuilder();
					Document doc = builder.parse(file);

					Source xmlFile = new StreamSource(file);
					SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
					Schema schema = schemaFactory.newSchema(new File("conf"+file.separator+"iTunes.xsd"));
					Validator validator = schema.newValidator();
					validator.validate(xmlFile);

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
