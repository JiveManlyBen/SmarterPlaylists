package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Smarter Playlists", "Content"));

    }

    public static Result about() {
        return ok(about.render("About", "All about the site"));
    }

}
