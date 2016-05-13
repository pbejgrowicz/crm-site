package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by pb on 13.05.2016.
 */


@Entity
public class Person extends Model {



        @Id
        public String id;

        public String name;
}
