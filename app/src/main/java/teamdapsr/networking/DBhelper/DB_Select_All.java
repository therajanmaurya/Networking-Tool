package teamdapsr.networking.DBhelper;

import com.activeandroid.query.Select;

import java.util.List;

import teamdapsr.networking.DB_Model.Ping_Host_Model;

/**
 * Created by rajanmaurya on 24/9/15.
 */
public class DB_Select_All {

    public static List<Ping_Host_Model> Select_All(){

        List<Ping_Host_Model> people = new Select()
                .all()
                .from(Ping_Host_Model.class)
                .execute();

        return people;
    }
}
