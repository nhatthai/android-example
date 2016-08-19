package demo.nhatthai.cafegrapp.presenter;

import java.util.Map;

import demo.nhatthai.cafegrapp.model.Feed;
import demo.nhatthai.cafegrapp.model.User;

import demo.nhatthai.cafegrapp.view.FeedDetailView;
import okhttp3.RequestBody;


/**
 * Created by nhatthai on 6/22/16.
 */
public class FeedDetailPresenterImpl implements FeedDetailPresenter, FeedDetailInteractor.OnFinishedListener{

    private FeedDetailView mFeedDetailView;
    private FeedDetailInteractor mFeedDetailInteractor;

    public FeedDetailPresenterImpl(FeedDetailView feedDetailView) {
        this.mFeedDetailView = feedDetailView;
        mFeedDetailInteractor = new FeedDetailInteractorImpl();
    }

    @Override
    public void onLoadFeedDetail(int id) {
        mFeedDetailInteractor.getFeedDetail(id, this);
    }

    @Override
    public void onSuccess(User user) {
        mFeedDetailView.onLoadFeedDetail(user);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onResponseError() {

    }

    @Override
    public void onFeedUpdate(int id, Feed feed) {
        mFeedDetailInteractor.onUpdateFeedById(id, feed, this);
    }

    @Override
    public void onCreateFeed(Map<String, RequestBody> map, Feed feed) {
        mFeedDetailInteractor.onCreatedFeed(map, feed, this);
    }

    @Override
    public void onSuccessCreate() {
        mFeedDetailView.onLoadFeeds();
    }
}
