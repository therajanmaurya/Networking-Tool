package teamdapsr.networking.Activity;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import teamdapsr.networking.R;

/**
 * Created by RajanMaurya on 16/11/15.
 */
public class MonitorDialog {

    Context  context;

    public MonitorDialog(Context context)
    {
        this.context = context;
    }

    public void startDialog(String title )
    {
        new MaterialDialog.Builder(context)
                .title(title)
                .customView(R.layout.materialdialog, true)
                .negativeText("OK")
                .show();
    }


}
