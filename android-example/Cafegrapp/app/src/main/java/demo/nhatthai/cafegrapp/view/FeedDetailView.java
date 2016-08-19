package demo.nhatthai.cafegrapp.view;

import demo.nhatthai.cafegrapp.model.User;

/**
 * Created by nhatthai on 6/22/16.
 */
public interface FeedDetailView {
    void onLoadFeedDetail(User user);

    void onLoadFeeds();
}
