package demo.nhatthai.cafegrapp.presenter;

import java.util.List;

import demo.nhatthai.cafegrapp.model.Feed;


/**
 * Created by nhatthai on 5/12/16.
 */
public interface FeedsInteractor {

    interface OnFinishedListener {
        void onReponseFinished(List<Feed> feeds);
        void onFinishedLoadMore(List<Feed> feeds);
        void onResponseFinished(Feed feed);
        void onFailure();
        void onSuccess();
        void onResponseError();
    }

    void getResponseItems(OnFinishedListener listener);

    void getResponseFeedsLoadMore(OnFinishedListener listener);

    void getResponseFeedsByUser(OnFinishedListener listener);

    void getFeedsLoadMoreByUser(OnFinishedListener listener);

    void getFeedById(OnFinishedListener listener, int feedId);
}