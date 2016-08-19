package demo.nhatthai.cafegrapp.presenter;

import java.util.Map;

import demo.nhatthai.cafegrapp.model.Feed;
import okhttp3.RequestBody;

/**
 * Created by nhatthai on 6/22/16.
 */
public interface FeedDetailPresenter {
    void onLoadFeedDetail(int id);

    void onFeedUpdate(int id, Feed feed);

    void onCreateFeed(Map<String, RequestBody> map, Feed feed);
}
