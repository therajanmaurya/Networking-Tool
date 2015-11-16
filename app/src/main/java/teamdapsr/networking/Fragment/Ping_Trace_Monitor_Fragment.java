/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package teamdapsr.networking.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.activeandroid.query.Delete;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import java.util.ArrayList;

import teamdapsr.networking.Activity.MonitorDialog;
import teamdapsr.networking.Activity.Ping_To_Domain;
import teamdapsr.networking.Activity.TraceActivity;
import teamdapsr.networking.Adapter.Manual_Ping_Adapter;
import teamdapsr.networking.Adapter.RecyclerItemClickListner;
import teamdapsr.networking.Custom_RecyclerView.Custom_RecyclerView;
import teamdapsr.networking.DB_Model.Date_Time_Model;
import teamdapsr.networking.DB_Model.Ping_Host_Model;
import teamdapsr.networking.DB_Model.Traceroute_model;
import teamdapsr.networking.DB_Model.Monitor_Model;
import teamdapsr.networking.DBhelper.DB_Add;
import teamdapsr.networking.DBhelper.DB_Select_All;
import teamdapsr.networking.R;
import teamdapsr.networking.Utils.Utils;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class Ping_Trace_Monitor_Fragment extends Fragment implements RecyclerItemClickListner.OnItemClickListener{

    private final String TAG = getClass().getSimpleName();

    protected Custom_RecyclerView mRecyclerView;
    protected Manual_Ping_Adapter mAdapter;
    protected Custom_RecyclerView.LayoutManager mLayoutManager;
	private int viewposition;
    private TextView mTextEmptyList;
    String LOG_TAG = getClass().getSimpleName();
    private EditText host;
    private View positiveAction;
    CheckBox Add_list ;
    boolean Add_list_status = false;
    public ArrayList<Ping_Host_Model> ping_host_models ;
	public ArrayList<Traceroute_model> traceroute_models;
	public ArrayList<Monitor_Model> monitor_models;


	@SuppressLint("ValidFragment")
	public Ping_Trace_Monitor_Fragment(int Position)
	{
		this.viewposition = Position;
	}

	public Ping_Trace_Monitor_Fragment()
	{

	}

    @Override
    public void onItemClick(View childView, int position) {
        Log.i(LOG_TAG, "The viewposition :" + position);
        startping(position);

    }

    @Override
    public void onItemLongPress(View childView, final int position) {

       final  int listPosition = position;
        new MaterialDialog.Builder(getActivity())
                .title("Are You Sure")
                .items(R.array.Long_click)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        if(which == 0){

                            /**
                             * Start Task OnLongPress The RecyclerView Item
                             */
                            startping(position);

                        }else if(which == 1){
                            // edit
                        }else if(which == 2){


                            /**
                             * Deleting the element from database
                             */
							if(viewposition == 1)
							{
								new Delete().from(Ping_Host_Model.class)
										.where("host = ?", ping_host_models.get(listPosition).getHost()).execute();
								ping_host_models.remove(listPosition);
								mAdapter.notifyDataSetChanged();
							}else if(viewposition == 2)
							{
								new Delete().from(Traceroute_model.class)
										.where("host = ?", traceroute_models.get(listPosition).getHost()).execute();
								traceroute_models.remove(listPosition);
								mAdapter.notifyDataSetChanged();
							}else if(viewposition == 3)
							{
								new Delete().from(Monitor_Model.class)
										.where("host = ?", monitor_models.get(listPosition).getHost()).execute();
								monitor_models.remove(listPosition);
								mAdapter.notifyDataSetChanged();
							}

                        }
                    }
                })
                .show();

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // ping_host_models = new ArrayList<>();
		ping_host_models = (ArrayList<Ping_Host_Model>) DB_Select_All.Select_AllPing();
		traceroute_models = (ArrayList<Traceroute_model>) DB_Select_All.Select_AllTrace();
		monitor_models = (ArrayList<Monitor_Model>) DB_Select_All.Select_AllMonitor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.manual_ping_recyclerview, container, false);
        rootView.setTag(TAG);
        mTextEmptyList = (TextView) rootView.findViewById(R.id.list_empty_message);
        mRecyclerView = (Custom_RecyclerView) rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListner(getActivity(), this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new Manual_Ping_Adapter(getActivity() , viewposition, ping_host_models , traceroute_models , monitor_models);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setEmptyView(mTextEmptyList);

        FloatingActionButton floatingActionButton = (FloatingActionButton)rootView.findViewById(R.id.ping_trace);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Material Dialog
                 */


                if(viewposition == 1)
                {
                    FabDialog(viewposition,"New Host" ,"Ping");
                }else if(viewposition == 2)
                {
                    FabDialog(viewposition,"New Host" ,"Trace Host");
                }else if(viewposition == 3)
				{
					FabDialog(viewposition,"New Host" ,"Monitor");
				}

            }
        });



        return rootView;
    }


    /**
     *  Saving all data to database
     * @param date current date to save in database
     * @param time  current time to save in database
     * @param host_id host or Ip to save in data base
	 * @param status of the monitor ping
     */
    public void saveTOdatabase(String host_id, String date , String time , @Nullable Boolean status)
    {

        DB_Add.AddToDatabse(viewposition, host_id, date, time);

        Date_Time_Model date_time_model = new Date_Time_Model(date ,time);

		if(viewposition == 1)
		{
			Ping_Host_Model ping_host_model = new Ping_Host_Model(host_id ,date_time_model);
			ping_host_models.add(ping_host_model);
			//mAdapter.notifyItemInserted(ping_host_models.size() - 1);
			Log.i(LOG_TAG, "save to database : " + ping_host_models.size());
			mAdapter.notifyDataSetChanged();

		}else if(viewposition ==2)
		{
			Traceroute_model traceroute = new Traceroute_model(host_id ,date_time_model);
			traceroute_models.add(traceroute);
			//mAdapter.notifyItemInserted(ping_host_models.size() - 1);
			Log.i(LOG_TAG, "save to database : " + traceroute_models.size());
			mAdapter.notifyDataSetChanged();
		}else if(viewposition == 3)
		{
			Monitor_Model monitor_model = new Monitor_Model(host_id , date_time_model, false);
			monitor_models.add(monitor_model);
			//mAdapter.notifyItemInserted(ping_host_models.size() - 1);
			Log.i(LOG_TAG, "save to database : " + monitor_models.size());
			mAdapter.notifyDataSetChanged();
		}



    }

	public void FabDialog(final int viewposition , String title , String positivetext)
	{
		MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
				.title(title)
				.customView(R.layout.dialog_customview, true)
				.positiveText(positivetext)
				.negativeText("CANCEL")
				.theme(Theme.LIGHT)
				.callback(new MaterialDialog.ButtonCallback() {
					@Override
					public void onPositive(MaterialDialog dialog) {

						if (Add_list_status) {

							//Add to database and open new activity to show ping

							Add_list_status = false;
							String host_data = host.getText().toString();
							String date = Utils.getCurrentDate();
							String time = Utils.getCurrentTime();

							saveTOdatabase(host_data, date, time , false);
							startactivity(viewposition, host_data, Ping_To_Domain.class, TraceActivity.class , Ping_To_Domain.class);
							Log.i(LOG_TAG, "list value" + ping_host_models.size());


						} else {
							// open new activity to show ping

							String host_data = host.getText().toString();

							startactivity(viewposition, host_data, Ping_To_Domain.class, TraceActivity.class , Ping_To_Domain.class);
							Log.i(LOG_TAG, "click list value" + ping_host_models.size());


						}


					}

					@Override
					public void onNegative(MaterialDialog dialog) {
					}
				}).build();

		positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
		host = (EditText) dialog.getCustomView().findViewById(R.id.password);
		Add_list = (CheckBox) dialog.getCustomView().findViewById(R.id.showPassword);

		Add_list.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.i(LOG_TAG, "" + isChecked);
                Add_list_status = isChecked;

            }
        });

		dialog.show();
	}


	public void startactivity(int position , @Nullable String host_data , @Nullable Class pingintent , @Nullable Class traceintent, @Nullable Class monitor)
	{

		switch (position)
		{
			case 1:
				Intent ping  = new Intent(getActivity(), pingintent);
				ping.putExtra(Ping_To_Domain.EXTRA_domain, host_data);
				startActivity(ping);
				return;
			case 2:
				Intent trace = new Intent(getActivity(), traceintent);
				trace.putExtra(TraceActivity.EXTRA_domain, host_data);
				startActivity(trace);
				return;
			case 3:
                MonitorDialog monitorDialog = new MonitorDialog(getActivity());
                monitorDialog.startDialog(host_data );
				return;

		}
		return;
	}


    public void startping(int itemposition)
    {
        String domain = null;

        if(viewposition == 1)
        {
            domain = ping_host_models.get(itemposition).getHost();

        }else if(viewposition == 2)
        {
            domain = traceroute_models.get(itemposition).getHost();

        }else if(viewposition == 3)
        {
            domain = monitor_models.get(itemposition).getHost();
        }

        Log.d(LOG_TAG ,"Domain is :" + domain);
        startactivity(viewposition, domain, Ping_To_Domain.class, TraceActivity.class, Ping_To_Domain.class);
    }



}
