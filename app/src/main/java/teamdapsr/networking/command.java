package teamdapsr.networking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by rajanmaurya on 13/9/15.
 */
public class command extends AppCompatActivity {


    Button button;
    TextView textView;
    EditText editText;
    Process mIpAddrProcess;
    String IPAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command);

        button  = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(null);
                IPAddress = editText.getText().toString();
                String PingCommand = "ping -c 1 -i .2 " + IPAddress;
                Runtime r = Runtime.getRuntime();
                Process p = null;
                BufferedReader in = null;
                String PingResult = "";

                try {
                    p = r.exec(PingCommand);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {

                    in = new BufferedReader(new
                            InputStreamReader(p.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println(inputLine);
                        textView.setText(inputLine + "\n\n");
                        PingResult += inputLine;
                        textView.setText(PingResult);
                    }
                    in.close();
                } catch (IOException | NullPointerException e1) {
                    e1.printStackTrace();
                }


            }

        });


    }
}
