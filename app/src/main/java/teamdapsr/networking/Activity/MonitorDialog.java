package teamdapsr.networking.Activity;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import teamdapsr.networking.R;
import teamdapsr.networking.connectionInfo.ConnectionDetector;
import teamdapsr.networking.ping.Ping;

/**
 * Created by RajanMaurya on 16/11/15.
 */
public class MonitorDialog {

    Context  context;
    ConnectionDetector connectionDetector;
    TextView networktype , mServiceresponse;
    Process process;
    Runtime runtime;
    private Handler handler;

    public MonitorDialog(Context context)
    {
        this.context = context;
        connectionDetector = new ConnectionDetector(context);
        handler = new Handler();
        runtime = Runtime.getRuntime();

    }

    public void startDialog(String host_data )
    {



        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title("Check: " + host_data)
                .customView(R.layout.monitordialog, true)
                .negativeText("OK")
                .build();

        networktype = (TextView) dialog.getCustomView().findViewById(R.id.connectiontype);
        networktype.setText("Network :" +connectionDetector.ConnectionType());

        mServiceresponse = (TextView) dialog.getCustomView().findViewById(R.id.serviceresponse);

        Ping ping = new Ping(context, process, runtime, host_data, mServiceresponse, handler);
        Thread thread = new Thread(ping);
        thread.start();

        dialog.show();

    }


}
