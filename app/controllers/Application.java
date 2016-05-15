package controllers;

import models.Project;
import models.Task;
import models.User;
import play.Routes;
import play.mvc.*;
import play.mvc.Security;

public class Application extends Controller {


    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(views.html.index.render(Project.findInvolving(request().username()),
                Task.findTodoInvolving(request().username()),
                User.find.byId(request().username())));

    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.Login.index());
    }


    public static Result redirectToUserList() {
        return redirect(routes.UserList.index());
    }

    public static Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
                Routes.javascriptRouter("jsRoutes",
                routes.javascript.Projects.add(),
                        routes.javascript.Projects.delete(),
                        routes.javascript.Projects.rename(),
                        routes.javascript.Projects.addGroup(),
                        routes.javascript.Tasks.add()
                )
        );
    }


}
