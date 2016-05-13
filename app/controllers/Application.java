package controllers;

import models.Person;
import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import play.data.Form;

import views.html.*;

import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Crm-site"));
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

    public static Result login() {
        //return ok(login.render());
        return ok();
    }

    public static class Login {
        public String email;
        public String password;
    }


}
