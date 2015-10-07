package teamdapsr.networking.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.apache.commons.net.whois.WhoisClient;
import java.io.IOException;
import java.net.SocketException;
import teamdapsr.networking.R;

/**
 * Created by rajan on 7/10/15.
 */
public class Whois extends Fragment {

	private TextView mWhois_text;
	private EditText mWhois_edit;
	private Button mWhois_button ;
	WhoisClient whoisClient ;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View whois_view = inflater.inflate(R.layout.whois, container, false);


		mWhois_text = (TextView)whois_view.findViewById(R.id.who_textview);
		mWhois_edit = (EditText)whois_view.findViewById(R.id.whois_edittext);
		mWhois_button = (Button)whois_view.findViewById(R.id.whois_button);
		mWhois_button.setOnClickListener(new whoisListener());

		return whois_view;
	}


	private class whoisListener implements android.view.View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			//ip = txtIp.getText().toString().trim();


			Thread thread = new Thread(new Runnable(){
				@Override
				public void run() {
					try {

						String nameToQuery = mWhois_edit.getText().toString();

						whoisClient = new WhoisClient();
						try {
							whoisClient.connect(WhoisClient.DEFAULT_HOST);
							String results = whoisClient.query("="+ nameToQuery + "\r\n");

							mWhois_text.setText(results);

							whoisClient.disconnect();
						} catch (SocketException e) {
							mWhois_text.setText("SocketException: "+e.toString());
						} catch (IOException e) {
							mWhois_text.setText("IOException: "+e.toString());
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			thread.start();



		}

	}

}
