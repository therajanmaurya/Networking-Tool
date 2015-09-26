package teamdapsr.networking.ping;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import teamdapsr.networking.R;

/**
 * Created by rajanmaurya on 18/9/15.
 */
public class Run_ping extends AppCompatActivity {


    Button button;
    TextView textView;
    EditText editText;
    Context context;
    Process process ;
    Runtime runtime ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command);

        button  = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);

        context = getApplicationContext();
        runtime = Runtime.getRuntime();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Ping ping = new Ping(context , process , runtime ,editText.getText().toString());
//                Thread thread = new Thread(ping);
//                thread.start();


            }

        });
    }
}
