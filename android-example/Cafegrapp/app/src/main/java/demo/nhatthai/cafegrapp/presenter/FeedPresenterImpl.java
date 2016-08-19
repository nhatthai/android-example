package demo.nhatthai.cafegrapp.presenter;

import java.util.List;
import demo.nhatthai.cafegrapp.model.Feed;
import demo.nhatthai.cafegrapp.view.FeedView;


/**
 * Created by nhatthai on 5/12/16.
 */
public class FeedPresenterImpl implements FeedPresenter, FeedsInteractor.OnFinishedListener {
    private FeedView feedView;
    private FeedsInteractor feedsInteractor;

    public FeedPresenterImpl(FeedView feedView) {
        this.feedView = feedView;
        feedsInteractor = new FeedsInteractorImpl();
    }

    /**
     * call Feed
     */
    @Override
    public void onFeedUpdate() {
        if (feedView != null) {
            feedView.showProgress();
        }
        feedsInteractor.getResponseItems(this);
    }

    /**
     * load more Feed
     */
    @Override
    public void onFeedMore() {
        if (feedView != null) {
            feedView.showProgress();
        }
        feedsInteractor.getResponseFeedsLoadMore(this);
    }

    /**
     * Finished load Feeds
     * @param feeds
     */
    @Override
    public void onReponseFinished(List<Feed> feeds) {
        if (feedView != null) {
            feedView.onLoadFeeds(feeds);
            feedView.hideProgress();
        }
    }

    /**
     *
     * @param feed
     */
    @Override
    public void onResponseFinished(Feed feed) {
        if (feedView != null) {
            feedView.onLoadFeed(feed);
            feedView.hideProgress();
        }
    }

    /**
     * Finished load more Feeds
     * @param feeds
     */
    @Override
    public void onFinishedLoadMore(List<Feed> feeds) {
        if (feedView != null) {
            feedView.onFinishLoadMore(feeds);
            feedView.hideProgress();
        }
    }

    /**
     * Get Feeds by User
     */
    @Override
    public void onFeedByUser() {
        if (feedView != null) {
            feedView.showProgress();
        }
        feedsInteractor.getResponseFeedsByUser(this);
    }

    /**
     * Get Feed more by user
     */
    @Override
    public void onFeedMoreByUser() {
        if (feedView != null) {
            feedView.showProgress();
        }
        feedsInteractor.getFeedsLoadMoreByUser(this);
    }

    @Override
    public void onGetFeedById(int feedId) {
        if (feedView != null) {
            feedView.showProgress();
        }
        feedsInteractor.getFeedById(this, feedId);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onResponseError() {

    }
}
