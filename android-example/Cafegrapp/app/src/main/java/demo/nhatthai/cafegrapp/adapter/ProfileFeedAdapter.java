package demo.nhatthai.cafegrapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.model.Feed;

/**
 * Created by nhatthai on 5/20/16.
 */
public class ProfileFeedAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<Feed> feedList = new ArrayList<>();
    private OnFeedItemClickListener mListener;
    private Context context;
    public ProfileFeedAdapter(Context context, ArrayList<Feed> feeds) {
        this.feedList = feeds;
        Log.d("Android", "Profile Feed" + feedList.size());
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setOnFeedItemClickListener(OnFeedItemClickListener listener) {
        this.mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.profile_feed_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Feed feedItem = feedList.get(position);
        viewHolder.setData(feedItem.getName(),
                feedItem.getUsername(), feedItem.getLike(), feedItem.getImageUrl(), context);

        viewHolder.mImgFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Android", "Click item image feed");
            }
        });
//
//        viewHolder.mImgProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.onProfileClick();
//            }
//        });

        viewHolder.mImgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView mImgLike = (ImageView) viewHolder.mImgLike;
                setImageLike(mImgLike, !mImgLike.getTag().toString().equals("heart_red"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    /**
     *
     */
    public interface OnFeedItemClickListener {
        void onProfileClick();
        void onLikeClick();
    }

    /*
     * Set Image Like or No(default)
     */
    private void setImageLike(ImageView mImgLike, boolean isLike) {
        if (isLike) {
            mImgLike.setImageResource(R.drawable.ic_heart_red);
            mImgLike.setTag("heart_red");
        } else {
            mImgLike.setImageResource(R.drawable.ic_heart_border);
            mImgLike.setTag("heart_border");
        }
    }
}
