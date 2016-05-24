package demo.nhatthai.cafegrapp.presenter;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import demo.nhatthai.cafegrapp.model.Feed;

/**
 * Created by nhatthai on 5/12/16.
 */
public class FindItemsInteractorImpl implements FindItemsInteractor {
    @Override public void findItems(final OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                listener.onFeedFinished(createFeedArrayList());
            }
        }, 2000);
    }

    private List<Feed> createFeedArrayList() {
        List<Feed> feeds = new ArrayList<Feed>();

        String imgURLs[] = {
            "http://www.localfoodtalks.co.uk/wp-content/uploads/2015/09/foodworld.jpg",
            "http://reachingutopia.com/wp-content/uploads/2014/04/healthy-wraps.jpg",
            "http://www.conagrafoods.ca/images/pam-background.jpg",
            "http://reachingutopia.com/wp-content/uploads/2014/04/healthy-wraps.jpg",
            "http://vignette4.wikia.nocookie.net/whatever-you-want/images/e/e0/Hd-food-wallpaperswallpapers-foods-pizza-food-hd-1920x1200-s8uotxhd.jpg",
            "http://las-vegas.eat24hours.com/files/cuisines/v4/healthy-foods.jpg",
            "http://reachingutopia.com/wp-content/uploads/2014/04/healthy-wraps.jpg",
            "http://las-vegas.eat24hours.com/files/cuisines/v4/healthy-foods.jpg",
            "http://www.conagrafoods.ca/images/bg1.jpg",
            "http://las-vegas.eat24hours.com/files/cuisines/v4/healthy-foods.jpg"};

        for (int i= 0; i < 4; i++) {
            String name = "Item " + i;
            String username = "User "+ i;
            String imgUrl = imgURLs[i];
            feeds.add(new Feed(name, imgUrl, username, false));
        }

        return feeds;
    }
}