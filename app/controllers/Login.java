package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Login extends Controller {

    private final static Form<LoginModel> loginForm = new Form<LoginModel>(LoginModel.class);

    public static Result index() {
        return ok(views.html.login.render(loginForm));
    }

    public static class LoginModel {

        public String email;
        public String password;

        public String validate() {
            if (User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }

    }

    public static Result authenticate() {
        Form<LoginModel> form = loginForm.bindFromRequest();
        DynamicForm requestData = Form.form().bindFromRequest();

        if(requestData.get("register")!=null){
            return redirect(routes.SignUp.index());
        }

        if(requestData.get("email")==null){
            return redirect(routes.Login.index());
        }


        if (form.hasErrors() || form.get().email.isEmpty() || form.get().password.isEmpty()) {
            return badRequest(views.html.login.render(form));
        } else {
            session().clear();
            session("email", form.get().email);
            return redirect(routes.Application.index());
        }
    }
}