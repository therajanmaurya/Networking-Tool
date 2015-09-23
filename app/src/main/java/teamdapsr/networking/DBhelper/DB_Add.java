package teamdapsr.networking.DBhelper;

import teamdapsr.networking.DB_Model.Ping_Host_Model;

/**
 * Created by rajanmaurya on 23/9/15.
 */
public class DB_Add {



    public  static void AddToDatabse(String host ,String date , String time){

        /**
         * Setting up the value
         */
        Ping_Host_Model ping_host_model = new Ping_Host_Model();
        ping_host_model.setHost(host);
        ping_host_model.setHost_date(date);
        ping_host_model.setHost_time(time);

        /**
         * Saving Value into Table using ActiveAndroid save() method
         */
        ping_host_model.save();

    }


}
