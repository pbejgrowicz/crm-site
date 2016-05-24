package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import com.avaje.ebean.*;
import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

/**
 * Created by pb on 15.05.2016.
 */
@Entity
public class Contact extends Model{

    @Id
    public Long id;

    @Constraints.Required
    public String firstname;

    @Constraints.Required
    public String lastname;

    @Constraints.Required
    public String number;


    public String email;


    public String company;


    public String userEmail;

   public static Finder<Long, Contact> find = new Finder<Long, Contact>(Long.class, Contact.class);

    public static List<Contact> returnListOfAllContacts() {

        return Contact.find.where().findList();
    }

}
