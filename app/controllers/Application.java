package controllers;

import models.Contact;
import models.User;
import play.Routes;
import static play.data.Form.*;

import play.data.Form;
import play.db.ebean.Model;
import play.mvc.*;

import views.html.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import play.api.libs.json.*;

public class Application extends Controller {


    @Security.Authenticated(Secured.class)
    public static Result index() {
        List<Contact> contacts = Contact.returnListOfAllContacts();

        //TODO: Sort contacts by lastname
        Collections.sort(contacts, new Comparator<Contact>() {
            public int compare(Contact c1, Contact c2) {
                return c1.lastname.compareTo(c2.lastname);
            }
        });

        return ok(views.html.index.render(
                User.find.byId(request().username()),
                contacts)
        );

    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(routes.Login.index());
    }


    public static Result redirectToUserList() {
        return redirect(routes.UserList.index());
    }

    @Security.Authenticated(Secured.class)
    public static Result edit(Long id) {
        Form<Contact> contactForm = form(Contact.class).fill(Contact.find.byId(id));
        return ok(views.html.editForm.render(id, contactForm, User.find.byId(request().username())));
    }

    public static Result update(Long id) {
        Form<Contact> contactForm = form(Contact.class).bindFromRequest();
        if(contactForm.hasErrors()) {
            return badRequest(editForm.render(id, contactForm, User.find.byId(request().username())));
        }
        contactForm.get().update(id);
        flash("success", "Computer " + contactForm.get().lastname + " " + contactForm.get().lastname + " has been updated");
        return redirect(routes.Application.index());
    }



    @Security.Authenticated(Secured.class)
    public static Result create() {
        Form<Contact> contactForm = form(Contact.class);
        return ok(
                views.html.createForm.render(contactForm, User.find.byId(request().username()))

        );
    }

    public static Result saveContact(String email) {
        Form<Contact> contactForm = form(Contact.class).bindFromRequest();
        if(contactForm.hasErrors()) {
            return badRequest(views.html.createForm.render(contactForm, User.find.byId(request().username())));
        }

        contactForm.get().userEmail = User.find.where().eq("email", email).findUnique().email; //Set contact to distinct User
        contactForm.get().save();

        flash("success", "Contact " + contactForm.get().firstname +" " + contactForm.get().lastname + " has been created");
        return redirect(routes.Application.index());
    }

    public static Result delete(Long id) {
        Contact.find.ref(id).delete();
        flash("success", "Computer has been deleted");
        return redirect(routes.Application.index());
    }





}
