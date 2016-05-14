package models;

import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pb on 15.05.2016.
 */
public class Contact extends Model{

    @Id
    public Long id;
    public String firstname;
    public String lastname;
    public String number;
    public String city;
    public String company;

    @ManyToMany(cascade = CascadeType.REMOVE)
    public List<User> members = new ArrayList<User>();

    public Contact(String firstname, String lastname, String number,
                   String city, String company, User owner) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.number = number;
        this.city = city;
        this.company = company;
        this.members.add(owner);
    }

    public static Model.Finder<Long,Contact> find = new Model.Finder(Long.class, Contact.class);

    public static Contact create(String firstname, String lastname, String number,
                                 String city, String company, String owner) {
        Contact newContact = new Contact(firstname, lastname, number, city, company, User.find.ref(owner));
        newContact.save();
        newContact.saveManyToManyAssociations("members");
        return newContact;
    }

    public static List<Contact> findInvolving(String user) {
        return find.where()
                .eq("members.email", user)
                .findList();
    }

    public static boolean isMember(Long contact, String user) {
        return find.where()
                .eq("members.email", user)
                .eq("id", contact)
                .findRowCount() > 0;
    }

    public static String rename(Long contactId, String newName) {
        Contact renamedContact = find.ref(contactId);
        renamedContact.firstname = newName;
        renamedContact.update();
        return newName;
    }
}
