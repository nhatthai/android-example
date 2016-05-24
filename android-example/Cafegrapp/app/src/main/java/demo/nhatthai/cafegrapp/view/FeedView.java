package demo.nhatthai.cafegrapp.view;

import java.util.List;

import demo.nhatthai.cafegrapp.model.Feed;

/**
 * Created by nhatthai on 5/12/16.
 */
public interface FeedView {

    void showProgress();

    void hideProgress();

    void showMessage(String message);

    void setFeedsFragment(List<Feed> feeds);

    void onItemClick(Feed feedItem);
}