package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import models.*;
/**
 * Created by pb on 14.05.2016.
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Login.index());
    }


}