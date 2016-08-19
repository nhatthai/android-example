package demo.nhatthai.cafegrapp.presenter;

import java.util.Map;

import demo.nhatthai.cafegrapp.model.Feed;
import demo.nhatthai.cafegrapp.model.User;
import okhttp3.RequestBody;

/**
 * Created by nhatthai on 6/22/16.
 */
public interface FeedDetailInteractor {

    interface OnFinishedListener {
        void onSuccess(User user);

        void onFailure();

        void onResponseError();

        void onSuccessCreate();
    }

    void getFeedDetail(int userId, final OnFinishedListener listener);

    void onUpdateFeedById(int id, Feed feed, final OnFinishedListener listener);

    void onCreatedFeed(Map<String, RequestBody> map, Feed feed, final OnFinishedListener listener);
}
