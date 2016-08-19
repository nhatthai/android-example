package demo.nhatthai.cafegrapp.presenter;

/**
 * Created by nhatthai on 5/12/16.
 */
public interface FeedPresenter {
    void onFeedUpdate();

    void onFeedMore();

    void onFeedByUser();

    void onFeedMoreByUser();

    void onGetFeedById(int feedId);
}