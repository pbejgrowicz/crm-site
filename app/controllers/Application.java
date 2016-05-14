package controllers;

import models.Person;
import models.Project;
import models.Task;
import models.User;
import play.db.ebean.Model;
import play.mvc.*;
import play.data.*;
import play.mvc.Security;

import views.html.*;

import java.util.List;

import static play.libs.Json.toJson;

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

    public static Result addPerson() {
        Person person = Form.form(Person.class).bindFromRequest().get();
        person.save();
        return redirect(routes.Application.index());
    }

    public static Result getPersons() {
        List<Person> persons = new Model.Finder(String.class, Person.class).all();
        return ok(toJson(persons));

    }


}
