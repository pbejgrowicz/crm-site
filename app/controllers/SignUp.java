package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;
import models.User;

/**
 * Created by pb on 14.05.2016.
 */
public class SignUp extends Controller {

    private final static Form<SignUpModel> signUpForm = new Form<SignUpModel>(SignUpModel.class);

    public static Result index() {
        return ok(views.html.register.render(signUpForm));
    }

    public static class SignUpModel {
        public String email;
        public String name;
        public String password;
    }

    public static Result addNewUser() {
        Form<SignUpModel> form = signUpForm.bindFromRequest();
        DynamicForm requestData = Form.form().bindFromRequest();

        if(requestData.get("goback")!=null){
            return redirect(routes.Application.index());
        }

        if (form.hasErrors()) {
            return badRequest(views.html.register.render(form));
        } else {
            User user;
            user = new User(form.get().email, form.get().name, form.get().password);
            user.save();
            session().clear();
            session("email", form.get().email);
            return redirect(routes.Application.index());
        }
    }

}
