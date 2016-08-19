package demo.nhatthai.cafegrapp.events;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


/**
 * Created by nhatthai on 6/10/16.
 */
public abstract class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int mVisibleThreshold = 4;

    // The current offset index of data you have loaded
    private int mCurrentPage = 0;

    // The total number of items in the dataset after the last load
    private int mPreviousTotalItemCount = 0;

    // True if we are still waiting for the last set of data to load.
    private boolean mLoading = true;

    // Sets the starting page index
    private int mStartingPageIndex = 0;

    private LinearLayoutManager mLinearLayoutManager;

    private final int VIEW_TYPE_ITEM = 0;

    public RecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.mLinearLayoutManager = layoutManager;
    }

    public RecyclerViewScrollListener(LinearLayoutManager layoutManager, int i) {
        this.mLinearLayoutManager = layoutManager;
        int mVisibleThreshold = i;
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        super.onScrolled(view, dx, dy);

        int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int visibleItemCount = view.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < mPreviousTotalItemCount) {
            this.mCurrentPage = this.mStartingPageIndex;
            this.mPreviousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.mLoading = true;
            }
        }
        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.

        //check has footerViewType
        int footerViewType = getFooterViewType();

        if (mLoading && (totalItemCount > mPreviousTotalItemCount)) {
            if (footerViewType == VIEW_TYPE_ITEM){
                mLoading = false;
                mPreviousTotalItemCount = totalItemCount;
            }
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        if (!mLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + mVisibleThreshold)) {
            mCurrentPage++;
            onLoadMore();
            mLoading = true;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //Call your method here for next set of data
            //onLoadMore();
        }
    }

    public void setLoaded() {
        mLoading = false;
    }

    // Defines the process for actually loading more data based on page
    public abstract void onLoadMore();

    public abstract int getFooterViewType();

}
