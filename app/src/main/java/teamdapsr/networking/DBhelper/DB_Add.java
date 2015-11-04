package teamdapsr.networking.DBhelper;

import teamdapsr.networking.DB_Model.Date_Time_Model;
import teamdapsr.networking.DB_Model.Ping_Host_Model;
import teamdapsr.networking.DB_Model.Traceroute_model;

/**
 * Created by rajanmaurya on 23/9/15.
 */
public class DB_Add {



    public  static void AddToDatabse(int position , String host ,String date , String time){

        /**
         * Setting up the value and Saving Value into Table using ActiveAndroid save() method
         */

        Date_Time_Model date_time_model = new Date_Time_Model(date ,time);

        date_time_model.save();

		switch (position)
		{
			case 1:
				Ping_Host_Model ping_host_model = new Ping_Host_Model(host ,date_time_model);
				ping_host_model.save();
				break;
			case 2:
				Traceroute_model traceroute_model = new Traceroute_model(host , date_time_model);
				traceroute_model.save();
				break;
		}




    }


}
