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

package teamdapsr.networking.Adapter;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import teamdapsr.networking.Custom_RecyclerView.Custom_RecyclerView;
import teamdapsr.networking.DB_Model.Ping_Host_Model;
import teamdapsr.networking.DB_Model.Traceroute_model;
import teamdapsr.networking.R;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class Manual_Ping_Adapter extends Custom_RecyclerView.Adapter<Manual_Ping_Adapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";


    public String LOG_TAG = getClass().getSimpleName();
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private ArrayList<Ping_Host_Model> Ping_array;
    private ArrayList<Traceroute_model> Trace_array;
    private int pagerposition;



    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends Custom_RecyclerView.ViewHolder {



        private final ImageView mImageView;
        private final TextView host;
        private final TextView Hdate;
        private final TextView Htime;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getPosition() + " clicked.");
                }
            });

            mImageView = (ImageView) v.findViewById(R.id.avatar);
            host = (TextView) v.findViewById(android.R.id.text1);
            Hdate = (TextView)v.findViewById(R.id.Host_date);
            Htime = (TextView) v.findViewById(R.id.Host_time);

        }

        public TextView gethost() {
            return host;
        }

        public TextView getDate(){
            return Hdate;
        }

        public TextView gettime(){
            return Htime;
        }

        public ImageView getmImageView() {
            return mImageView;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param ping_host_modelArryList String[] containing the data to populate views to be used by RecyclerView.
     */
    public Manual_Ping_Adapter(Context context, int Position , ArrayList<Ping_Host_Model> ping_host_modelArryList , ArrayList<Traceroute_model> traceroute_models) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.pagerposition = Position;
        this.Ping_array =  ping_host_modelArryList;
        this.Trace_array = traceroute_models;
    }




    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        v.setBackgroundResource(mBackground);
        v.setSelected(true);
        return new ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        if(pagerposition == 1)
        {
            viewHolder.gethost().setText(Ping_array.get(position).getHost());
            viewHolder.getDate().setText(Ping_array.get(position).getDate_time_model().getHost_date());
            viewHolder.gettime().setText(Ping_array.get(position).getDate_time_model().getHost_time());
            viewHolder.getmImageView().setImageResource(R.drawable.cheese_1);
        }else if(pagerposition == 2)
        {
            viewHolder.gethost().setText(Trace_array.get(position).getHost());
            viewHolder.getDate().setText(Trace_array.get(position).getDate_time_model().getHost_date());
            viewHolder.gettime().setText(Trace_array.get(position).getDate_time_model().getHost_time());
            viewHolder.getmImageView().setImageResource(R.drawable.cheese_2);
        }


    }


    @Override
    public int getItemCount() {
        switch (pagerposition){
            case 1:
                return Ping_array.size();
            case 2:
                return Trace_array.size();
        }
        return 0;
    }
}
