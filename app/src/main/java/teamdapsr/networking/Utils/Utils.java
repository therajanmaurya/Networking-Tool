package teamdapsr.networking.Utils;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by rajanmaurya on 24/9/15.
 */
public class Utils {

    private static Context context;

    public Utils(Context context){

        this.context = context;

    }

    /**
     *
     * @return Current date
     */
    public static String getCurrentDate()
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c.getTime());
    }


    /**
     *
     * @return Current Time
     */
    public static String getCurrentTime()
    {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}
