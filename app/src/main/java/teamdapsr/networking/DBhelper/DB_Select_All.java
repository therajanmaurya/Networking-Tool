package teamdapsr.networking.DBhelper;

import com.activeandroid.query.Select;

import java.util.List;

import teamdapsr.networking.DB_Model.Ping_Host_Model;
import teamdapsr.networking.DB_Model.Traceroute_model;

/**
 * Created by rajanmaurya on 24/9/15.
 */
public class DB_Select_All {

    public static List<Ping_Host_Model> Select_AllPing(){

        return new Select()
                .all()
                .from(Ping_Host_Model.class)
                .execute();
    }

	public static List<Traceroute_model> Select_AllTrace(){

		return new Select()
				.all()
				.from(Traceroute_model.class)
				.execute();
	}
}
