package teamdapsr.networking.DBhelper;

import com.activeandroid.query.Select;

import java.util.List;

import teamdapsr.networking.DB_Model.Ping_Host_Model;

/**
 * Created by RajanMaurya on 24/09/15.
 */
public class DB_Get_All {


    /**
     *
     * @return Getting All elements from database ;
     */
    public static List<Ping_Host_Model> Get_All_Host(){

        return new Select().from(Ping_Host_Model.class).orderBy("Name ASC").limit(100).execute();
    }

}
