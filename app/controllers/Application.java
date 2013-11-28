package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Smarter Playlists", "home", "Content"));

    }

    public static Result about() {
        return ok(index.render("About", "about", "All about the site"));
    }

}
