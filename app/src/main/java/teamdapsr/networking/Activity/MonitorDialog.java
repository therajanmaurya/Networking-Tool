package teamdapsr.networking.Activity;

import android.content.Context;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import teamdapsr.networking.R;
import teamdapsr.networking.connectionInfo.ConnectionDetector;

/**
 * Created by RajanMaurya on 16/11/15.
 */
public class MonitorDialog {

    Context  context;
    ConnectionDetector connectionDetector;
    TextView networktype;

    public MonitorDialog(Context context)
    {
        this.context = context;
        connectionDetector = new ConnectionDetector(context);

    }

    public void startDialog(String title )
    {

        MaterialDialog dialog = new MaterialDialog.Builder(context)
                .title("Check: " +title)
                .customView(R.layout.monitordialog, true)
                .negativeText("OK")
                .build();

        networktype = (TextView) dialog.getCustomView().findViewById(R.id.connectiontype);
        networktype.setText("Network Connection :" +connectionDetector.ConnectionType());

        dialog.show();

    }


}
