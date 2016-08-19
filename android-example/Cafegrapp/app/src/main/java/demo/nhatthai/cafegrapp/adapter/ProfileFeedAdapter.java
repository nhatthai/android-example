package demo.nhatthai.cafegrapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
    private Context mContext;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private BaseAdapter.ViewHolder mViewHolder;

    public ProfileFeedAdapter(Context context, ArrayList<Feed> feeds) {
        this.feedList = feeds;
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void setOnFeedItemClickListener(OnFeedItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return feedList.get(position) != null ? VIEW_TYPE_ITEM : VIEW_TYPE_LOADING;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            return new ViewHolder(mLayoutInflater
                    .inflate(R.layout.profile_feed_item, viewGroup, false));
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = mLayoutInflater.inflate(R.layout.layout_loading_item, viewGroup, false);
            return new FeedAdapter.LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        final Feed feedItem = feedList.get(position);

        if (viewHolder instanceof BaseAdapter.ViewHolder) {
            mViewHolder = (ViewHolder) viewHolder;

            mViewHolder.setData(feedItem.getName(),
                    feedItem.getUsername(), feedItem.getLike(), feedItem.getImageUrl(),
                    "", feedItem.getFoodType(), feedItem.getRating(),
                    String.valueOf(feedItem.getTime()), mContext);

            mViewHolder.mImgFeed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFeedImageClick(feedItem);
                }
            });

            mViewHolder.mImgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView mImgLike = (ImageView) mViewHolder.mImgLike;
                    setImageLike(mImgLike, !mImgLike.getTag().toString().equals("heart_red"));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    /**
     *
     */
    public interface OnFeedItemClickListener {
        void onFeedImageClick(Feed feedItem);
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
