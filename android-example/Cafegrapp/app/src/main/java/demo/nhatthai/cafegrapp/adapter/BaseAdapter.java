package demo.nhatthai.cafegrapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import com.bumptech.glide.Glide;

import demo.nhatthai.cafegrapp.R;

/**
 * Created by nhatthai on 5/20/16.
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // Views
        private TextView mTxtViewName, mTxtFeedName, mTxtType, mTxtRating, mTxtTime;
        ImageView mImgFeed, mImgLike, mImgProfile;

        // constructor
        ViewHolder(View itemView) {
            super(itemView);

            mTxtViewName = (TextView) itemView.findViewById(R.id.txt_name);
            mImgFeed = (ImageView) itemView.findViewById(R.id.img_feed);
            mTxtFeedName = (TextView) itemView.findViewById(R.id.txt_feed_title);
            mImgLike = (ImageView) itemView.findViewById(R.id.img_like);
            mImgProfile = (ImageView) itemView.findViewById(R.id.img_profile);

            mTxtType = (TextView) itemView.findViewById(R.id.txt_type);
            mTxtRating = (TextView) itemView.findViewById(R.id.txt_rating);
            mTxtTime = (TextView) itemView.findViewById(R.id.txt_time_ago);
        }

        // set data
        public void setData(String name, String username, boolean isLike) {
            mTxtViewName.setText(username);
            mTxtFeedName.setText(name);

            if (isLike) {
                mImgLike.setImageResource(R.drawable.ic_heart_red);
                mImgLike.setTag("heart_red");
            } else {
                mImgLike.setImageResource(R.drawable.ic_heart_border);
                mImgLike.setTag("heart_border");
            }

        }

        // set data
//        public void setData(String name, String username, boolean isLike, String imgUrl, Context context) {
//            //set image for feed item
//            Glide.with(context).load(imgUrl).centerCrop().into(mImgFeed);
//
//            setData(name, username, isLike);
//        }

//        public void setData(int feedId, String name, String username, boolean isLike, String imgUrl,
//                            String imgAvatarUrl, String type, String rating, String time,
//                            Context context) {
//
////            setData(String name, String username, boolean isLike, String imgUrl,
////                    String imgAvatarUrl, String type, String rating, String time,
////                    Context context);
//
//        }

        public void setData(String name, String username, boolean isLike, String imgUrl,
                            String imgAvatarUrl, String type, String rating, String time,
                            Context context) {

            //set image for feed item
            Glide.with(context).load(imgUrl).centerCrop().into(mImgFeed);

            if (imgAvatarUrl != "")
                //set image for user profile
                Glide.with(context).load(imgAvatarUrl).centerCrop().into(mImgProfile);

            setData(name, username, isLike);

            mTxtType.setText(type);
            mTxtRating.setText(rating + "/10");
            mTxtTime.setText(time + " minutes");
        }
    }
}

