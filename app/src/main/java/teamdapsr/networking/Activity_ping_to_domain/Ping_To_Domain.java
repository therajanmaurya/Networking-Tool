package teamdapsr.networking.Activity_ping_to_domain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import teamdapsr.networking.R;

/**
 * Created by rajanmaurya on 25/9/15.
 */
public class Ping_To_Domain  extends AppCompatActivity{

    public static final String EXTRA_domain= "domain";
    private TextView domain_name ;
    String domain ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ping_to_domain);
        Intent intent = getIntent();
        domain = intent.getStringExtra(EXTRA_domain);
        domain_name = (TextView)findViewById(R.id.ping_to_domain);
        domain_name.setText(domain);

    }
}
