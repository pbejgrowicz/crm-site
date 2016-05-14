package controllers;

import models.Person;
import models.Project;
import models.Task;
import models.User;
import play.Routes;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by pb on 15.05.2016.
 */
public class UserList extends Controller{

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(views.html.users.render(Project.findInvolving(request().username()),
                Task.findTodoInvolving(request().username()),
                User.find.byId(request().username())));

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
