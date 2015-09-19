package teamdapsr.networking.Su;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import teamdapsr.networking.R;

/**
 * Created by rajanmaurya on 16/9/15.
 */
public class Su_permission implements Runnable
{

    public Context Su_permission_context;

    String LOG_TAG = getClass().getName();

    /**
     * "su" shell command to check super user permission
     */
    String pingcommand = "su";

    public Su_permission(Context application_context) {
        this.Su_permission_context = application_context;
    }


    /**
     *
     * @param host is the su command to get superuser permission
     * @throws IOException get the IOException "Any other command is running in shell"
     *
     */
    public void get_Su_permission(String host) throws IOException, InterruptedException {

        Runtime suRuntime = Runtime.getRuntime();
        Process process = suRuntime.exec(host);
        process.waitFor();
        int exitvalue = process.exitValue();


        /**
         * saving superuser permission state in sharedprefrences
         */
        SharedPreferences su_prefrence = Su_permission_context
                .getSharedPreferences(Su_permission_context
                        .getString(R.string.superuser), Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = su_prefrence.edit();

        Log.i(LOG_TAG , ""+ exitvalue);

        if(process.exitValue() != 255 )
        {
            editor.putString(Su_permission_context.getString(R.string.superuser),Su_permission_context.getString(R.string.su_Y));
            editor.apply();
            Log.i(LOG_TAG , "permission granted");
        }else
        {

            editor.putString(Su_permission_context.getString(R.string.superuser),Su_permission_context.getString(R.string.su_N));
            editor.apply();
            Log.i(LOG_TAG, "NO permission granted");
        }


    }


    /**
     * Running "su" in new thread
     */
    @Override
    public void run()
    {

        try {
            get_Su_permission(pingcommand);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
