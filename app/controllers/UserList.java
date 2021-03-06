package controllers;

import models.Contact;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by pb on 15.05.2016.
 */
public class UserList extends Controller{

    @Security.Authenticated(Secured.class)
    public static Result index() {
        if (User.find.byId(request().username()).isSuperUser == true)
        return ok(views.html.users.render(

                User.find.byId(request().username()),
                User.returnListOfAllUsers())
        );
        else
            return ok(views.html.index.render(
                User.find.byId(request().username()),
                    Contact.returnListOfAllContacts())
                );

    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.Login.index());
    }

    public static Result redirectToMainIndex() {
        return redirect(routes.Application.index());
    }




}
