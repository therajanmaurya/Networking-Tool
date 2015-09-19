package teamdapsr.networking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import teamdapsr.networking.CheeseDetailActivity;
import teamdapsr.networking.Model.Model;
import teamdapsr.networking.R;

/**
 * Created by rajanmaurya on 19/9/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<String> mValues;
        private String date;
        private String time;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;

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
            return mValues.get(position);
        }

        public RecyclerViewAdapter(Context context, List<String> items, String datetime, String currentTime) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
            this.date = datetime;
            this.time = currentTime;
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
            holder.mBoundString = mValues.get(position);
            holder.mTextView.setText(mValues.get(position));
            holder.Hdate.setText(date);
            holder.Htime.setText(time);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, CheeseDetailActivity.class);
                    intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.mBoundString);

                    context.startActivity(intent);
                }
            });

            Glide.with(holder.mImageView.getContext())
                    .load(Model.getRandomCheeseDrawable())
                    .fitCenter()
                    .into(holder.mImageView);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
