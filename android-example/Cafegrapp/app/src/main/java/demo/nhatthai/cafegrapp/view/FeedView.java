package demo.nhatthai.cafegrapp.view;

import java.util.List;

import demo.nhatthai.cafegrapp.model.Feed;
import demo.nhatthai.cafegrapp.model.FoodType;

/**
 * Created by nhatthai on 5/12/16.
 */
public interface FeedView {

    void showProgress();

    void hideProgress();

    void onSuccess();

    void onLoadFeeds(List<Feed> feeds);

    void onLoadFeed(Feed feed);

    void onFinishLoadMore(List<Feed> feeds);

    void onLoadFoodType(List<FoodType> foodTypes);
}