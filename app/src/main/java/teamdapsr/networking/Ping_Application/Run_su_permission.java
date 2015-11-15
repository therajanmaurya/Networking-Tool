package teamdapsr.networking.Ping_Application;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;

/**
 * Created by rajanmaurya on 16/9/15.
 */
public class Run_su_permission extends Application
{

    public Context Application_context ;

    //Su_permission su_permission ;

    @Override
    public void onCreate()
    {
        super.onCreate();


        /**
         * initialization of Active android
         */
        ActiveAndroid.initialize(this);

       // Application_context = getApplicationContext();

        /**
         * starting su_permission new thread
         */
        /*su_permission = new Su_permission(Application_context);
        Thread thread = new Thread(su_permission);
        thread.start();*/

    }
}
