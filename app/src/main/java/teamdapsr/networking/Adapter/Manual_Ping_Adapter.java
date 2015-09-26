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
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import teamdapsr.networking.DB_Model.Ping_Host_Model;
import teamdapsr.networking.R;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class Manual_Ping_Adapter extends RecyclerView.Adapter<Manual_Ping_Adapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";


    public String LOG_TAG = getClass().getSimpleName();
    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private ArrayList<Ping_Host_Model> Ping_array;



    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {



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
    public Manual_Ping_Adapter(Context context, ArrayList<Ping_Host_Model> ping_host_modelArryList) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.Ping_array =  ping_host_modelArryList;
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

        viewHolder.gethost().setText(Ping_array.get(position).getHost());
        viewHolder.getDate().setText(Ping_array.get(position).getDate_time_model().getHost_date());
        viewHolder.gettime().setText(Ping_array.get(position).getDate_time_model().getHost_time());
        viewHolder.getmImageView().setImageResource(R.drawable.cheese_1);

    }


    @Override
    public int getItemCount() {
        return Ping_array.size();
    }
}
