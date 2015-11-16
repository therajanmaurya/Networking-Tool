package teamdapsr.networking.connectionInfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by RajanMaurya on 16/11/15.
 */
public class ConnectionDetector {

    private Context _context;


    public ConnectionDetector(Context context) {
        this._context = context;
    }


    /**
     * Checking Internet connection is connected or not
     * if Connected return True Else return False
     *
     * @return True For Connected , False For Not Connected
     */
    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

            // getActiveNetworkInfo return true if device is connected to network else return null
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
                return true;

        }
        return false;
    }

    public String ConnectionType()
    {
        if(Connectivity.isConnectedMobile(_context))
        {
            return "Data Connection";
        }else if(Connectivity.isConnectedWifi(_context))
        {
            return "Wifi Connection";
        }

        return null;
    }

}
