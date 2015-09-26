package teamdapsr.networking.ping;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by rajanmaurya on 16/9/15.
 */
public class Ping implements Runnable
{

    private String LOG_TAG = getClass().getName();
    private Context Ping_context;
    private Process process;
    private Runtime runtime;
    private String host;
    private BufferedReader in = null;
    private String PingResult = "";
    public TextView data ;
    public Handler handler ;


    @Override
    public void run()
    {
        try
        {
            PingRequest();
        } catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param context Getting Application Context
     * @param process   Process variable to handle process
     * @param runtime   Runtime to command in terminal
     * @param host      host value (IP Address to Ping)
     */
    public Ping(Context context , Process process , Runtime runtime , String host , TextView data  ,Handler handler)
    {
        this.Ping_context = context;
        this.process = process;
        this.runtime = runtime;
        this.host = host;
        this.data = data;
        this.handler = handler;

    }

    /**
     * Running Ping command in Android shell
     *
     * @throws IOException  IO exception during running command
     * @throws InterruptedException  Any Interruption
     */
    public void PingRequest() throws IOException, InterruptedException
    {

            process = runtime.exec("ping -i .2 -c 3 "+host);
            process.waitFor();
            PingData(process);

    }


    /**
     * Getting Ping data from Android Terminal
     *
     * @param process
     * @throws IOException
     * @throws NullPointerException
     */
    public void PingData( final Process process) throws IOException , NullPointerException{


        handler.post(new Runnable() {
            @Override
            public void run() {
                in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String inputLine;
                try {
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println(inputLine);
                        PingResult += inputLine;
                        Log.i(LOG_TAG, "INTO Handler");
                        data.setText(PingResult);

                    }
                    in.close();
                } catch (IOException e) {
                    data.setText("Host Unreachable");
                    e.printStackTrace();
                }


                PingDataAnalysis(PingResult);
            }
        });


    }


    /**
     *
     * Analysing Ping data "What happen to command , it executed successfully or not or it is failed"
     *
     * @param fullString String out of Ping Request
     */
    public void PingDataAnalysis(String fullString){

        boolean Unreachable = fullString.contains("Destination Host Unreachable");
        Log.i(LOG_TAG , "" + Unreachable);
        boolean unknown_host = fullString.contains("unknown host");
        Log.i(LOG_TAG,"" + unknown_host);
        boolean packetLoss = fullString.contains("1 packets transmitted, 0 received, 100% packet loss");
        Log.i(LOG_TAG , "" + packetLoss);
        boolean packetRecieved = fullString.contains("1 packets transmitted, 1 received, 0% packet loss");
        Log.i(LOG_TAG , "" + packetRecieved);

        if (Unreachable)
        {
            Log.i(LOG_TAG , "Destination Host Unreachable");
        }else if (unknown_host)
        {
            Log.i(LOG_TAG , "unknown host");
        }else if (packetLoss)
        {
            Log.i(LOG_TAG , "Host is dead");
        }else if(packetRecieved)
        {
            Log.i(LOG_TAG , "Host is live");
        }
    }

}
