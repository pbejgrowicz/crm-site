package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import java.util.*;

import models.*;
import views.html.tasks.*;


@Security.Authenticated(Secured.class)
public class Tasks extends Controller {
    public static Result index(Long project) {
        if(Secured.isMemberOf(project)) {
            return ok(
                    index.render(
                            Project.find.byId(project),
                            Task.findByProject(project)
                    )
            );
        } else {
            return forbidden();
        }
    }

    public static Result add(Long project, String folder) {
        if(Secured.isMemberOf(project)) {
            Form<Task> taskForm = form(Task.class).bindFromRequest();
            if(taskForm.hasErrors()) {
                return badRequest();
            } else {
                return ok(
                        item.render(Task.create(taskForm.get(), project, folder))
                );
            }
        } else {
            return forbidden();
        }
    }

}