import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;
import com.avaje.ebean.Ebean;
import java.util.*;
import models.*;

/**
 * Created by pb on 13.05.2016.
 */
public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        //Check if the datebase is empty
        if(User.find.findRowCount() == 0) {
            Ebean.save((List) Yaml.load("initial-data.yml"));
        }
    }

}
