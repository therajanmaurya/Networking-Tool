/*
 * Copyright (C) 2015 The Android Open Source Project
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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.activeandroid.util.Log;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import teamdapsr.networking.Adapter.RecyclerViewAdapter;
import teamdapsr.networking.MainActivity;
import teamdapsr.networking.DB_Model.Model;
import teamdapsr.networking.DB_Model.Ping_Host_Model;
import teamdapsr.networking.R;

public class Ping_RecyclerView extends Fragment {

    String LOG_TAG = getClass().getName();
    private RecyclerView recyclerView ;
    private EditText host;
    private View positiveAction;
    CheckBox Add_list ;
    boolean Add_list_status = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_cheese_list, container, false);

        recyclerView = (RecyclerView)layout.findViewById(R.id.recyclerview);
        setupRecyclerView(recyclerView);

        MainActivity.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                        .title("New Host")
                        .customView(R.layout.dialog_customview, true)
                        .positiveText("Ping")
                        .negativeText("Cancel")
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {

                                if(Add_list_status){

                                    //Add to database and open new activity

                                    String host_data = host.getText().toString();
                                    String date = getCurrentDate();
                                    String time = getCurrentTime();
                                    saveTOdatabase(date,time,host_data);

                                }else
                                {
                                    String hostvalue = host.getText().toString();

                                    // open new activity to show ping
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
        });

        return layout;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new RecyclerViewAdapter(getActivity(), getRandomSublist(Model.sCheeseStrings, 30),getCurrentDate(),getCurrentTime()));
    }

    private List<String> getRandomSublist(String[] array, int amount) {
        ArrayList<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }


    /**
     *
     * @param date current date to save in database
     * @param time  current time to save in database
     * @param host_id host or Ip to save in data base
     */
    public void saveTOdatabase(String date , String time , String host_id)
    {

        // saving to data base;
        Ping_Host_Model ping_hostModel = new Ping_Host_Model(date, time,host_id);
        ping_hostModel.save();

    }


    /**
     *
     * @return Current date
     */
    public String getCurrentDate()
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c.getTime());
    }


    /**
     *
     * @return Current Time
     */
    public static String getCurrentTime()
    {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
    }

}
