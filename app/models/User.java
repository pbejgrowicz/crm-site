package models;

import play.db.ebean.Model;
import com.avaje.ebean.*;
import javax.persistence.*;
import java.util.List;


/**
 * Created by pb on 13.05.2016.
 */


@Entity
public class User extends Model {

    @Id
    public String email;
    public String name;
    public String password;
    public Boolean isSuperUser;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.isSuperUser = false;
    }

    public static Finder<String,User> find = new Finder<String, User>(String.class, User.class);

    public static User authenticate(String email, String password) {
        return find.where().eq("email", email).eq("password", password).findUnique();
    }

    public static List<User> returnListOfAllUsers() {
        return User.find.where().findList();
    }




}
