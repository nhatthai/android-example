package demo.nhatthai.cafegrapp.presenter;

import java.util.List;

import demo.nhatthai.cafegrapp.model.Feed;


/**
 * Created by nhatthai on 5/12/16.
 */
public interface FindItemsInteractor {

    interface OnFinishedListener {
        void onFeedFinished(List<Feed> feeds);
    }

    void findItems(OnFinishedListener listener);
}