package teamdapsr.networking.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by rajanmaurya on 19/9/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        public String LOG_TAG = getClass().getSimpleName();
        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private ArrayList<Ping_Host_Model> Ping_array;

    public static class ViewHolder extends RecyclerView.ViewHolder {
            //public String mBoundString;

            public final View mView;
            public final ImageView mImageView;
            public final TextView mTextView;
            public final TextView Hdate;
            public final TextView Htime;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (ImageView) view.findViewById(R.id.avatar);
                mTextView = (TextView) view.findViewById(android.R.id.text1);
                Hdate = (TextView) view.findViewById(R.id.Host_date);
                Htime = (TextView) view.findViewById(R.id.Host_time);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }

        public String getValueAt(int position) {
            return Ping_array.get(position).getHost();
        }

        public RecyclerViewAdapter(Context context, ArrayList<Ping_Host_Model> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            this.Ping_array =  items;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mTextView.setText(Ping_array.get(position).getHost());
            holder.Hdate.setText(Ping_array.get(position).getDate_time_model().getHost_date());
            holder.Htime.setText(Ping_array.get(position).getDate_time_model().getHost_time());

            /*holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, CheeseDetailActivity.class);
                    intent.putExtra(CheeseDetailActivity.EXTRA_NAME, "fgdhgjhb");

                    context.startActivity(intent);
                }
            });*/

            holder.mImageView.setImageResource(R.drawable.cheese_1);

//            Glide.with(holder.mImageView.getContext())
//                    .load(Model.getRandomCheeseDrawable())
//                    .fitCenter()
//                    .into(holder.mImageView);
        }

        @Override
        public int getItemCount() {
            //Log.i(LOG_TAG , "" + Ping_array.size());
            return Ping_array.size();
        }
    }
