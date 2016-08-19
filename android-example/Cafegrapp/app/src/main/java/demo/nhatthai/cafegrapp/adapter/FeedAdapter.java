package demo.nhatthai.cafegrapp.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.support.v7.widget.RecyclerView;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.model.Feed;

/**
 * Created by nhatthai on 5/12/16.
 */
public class FeedAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<Feed> feedList = new ArrayList<>();
    private OnFeedItemClickListener mListener;
    private Context context;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private ViewHolder mViewHolder;

    public FeedAdapter(Context context, List<Feed> feeds) {
        this.feedList = feeds;
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
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
                    .inflate(R.layout.feed_item, viewGroup, false));
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = mLayoutInflater.inflate(R.layout.layout_loading_item, viewGroup, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        try {
            if (viewHolder instanceof ViewHolder) {
                mViewHolder = (ViewHolder) viewHolder;
                final Feed feedItem = feedList.get(position);
                mViewHolder.setData(feedItem.getName(),
                        feedItem.getUsername(), feedItem.getLike(), feedItem.getImageUrl(),
                        feedItem.getImgUserUrl(), feedItem.getFoodType(), feedItem.getRating(),
                        String.valueOf(feedItem.getTime()), context);

                mViewHolder.mImgFeed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onFeedImageClick(feedItem);
                    }
                });

                mViewHolder.mImgProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onProfileClick(feedItem);
                    }
                });

                mViewHolder.mImgLike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImageView mImgLike = (ImageView) mViewHolder.mImgLike;
                        setImageLike(mImgLike, !mImgLike.getTag().toString().equals("heart_red"));
                    }
                });

            } else if (viewHolder instanceof LoadingViewHolder) {
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) viewHolder;
                loadingViewHolder.progressBar.setIndeterminate(true);
            }

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
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
        void onProfileClick(Feed feedItem);
        void onFeedImageClick(Feed feedItem);
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

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_loading);
        }
    }
}


