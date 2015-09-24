package teamdapsr.networking.DBhelper;

import teamdapsr.networking.DB_Model.Date_Time_Model;
import teamdapsr.networking.DB_Model.Ping_Host_Model;

/**
 * Created by rajanmaurya on 23/9/15.
 */
public class DB_Add {



    public  static void AddToDatabse(String host ,String date , String time){

        /**
         * Setting up the value
         */

        Date_Time_Model date_time_model = new Date_Time_Model(date ,time);

        date_time_model.save();
        Ping_Host_Model ping_host_model = new Ping_Host_Model(host ,date_time_model);


        /**
         * Saving Value into Table using ActiveAndroid save() method
         */
        ping_host_model.save();



    }


}
