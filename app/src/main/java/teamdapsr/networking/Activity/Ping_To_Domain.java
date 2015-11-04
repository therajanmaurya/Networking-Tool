package teamdapsr.networking.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import teamdapsr.networking.R;
import teamdapsr.networking.ping.Ping;

/**
 * Created by rajanmaurya on 25/9/15.
 */
public class Ping_To_Domain  extends AppCompatActivity{

    public String  LOG_TAG = getClass().getSimpleName();
    public static final String EXTRA_domain= "domain";
    private TextView domain_name , pingdata ;
    String domain , result  = "";
    private Handler handler;
    Process process ;
    Runtime runtime ;
    private Toolbar ping_toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ping_to_domain);

        handler = new Handler();
        /**
         * Domain or IP Address to Ping
         */
        Intent intent = getIntent();
        domain = intent.getStringExtra(EXTRA_domain);

        ping_toolbar = (Toolbar) findViewById(R.id.toolbar1);
        ping_toolbar.setTitle(domain);
        setSupportActionBar(ping_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        domain_name = (TextView)findViewById(R.id.IP);
        pingdata = (TextView)findViewById(R.id.PingData);
        domain_name.setText(domain);

        /**
         * Running Ping Command in Thread for Good Efficiency
         */
        runtime = Runtime.getRuntime();
        Ping ping = new Ping(getApplicationContext() , process , runtime ,domain , pingdata , handler);
                Thread thread = new Thread(ping);
                thread.start();


}
}

