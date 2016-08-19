package demo.nhatthai.cafegrapp.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import demo.nhatthai.cafegrapp.model.Feed;
import demo.nhatthai.cafegrapp.model.User;
import demo.nhatthai.cafegrapp.service.FeedService;
import demo.nhatthai.cafegrapp.service.GeneratorService;
import demo.nhatthai.cafegrapp.service.GeoService;
import demo.nhatthai.cafegrapp.service.GeoServiceImpl;
import demo.nhatthai.cafegrapp.settings.Session;
import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nhatthai on 6/22/16.
 */
public class FeedDetailInteractorImpl implements FeedDetailInteractor {

    @Override
    public void getFeedDetail(int userId, final OnFinishedListener listener) {
        GeoService service = GeoServiceImpl.getService();
        Call<User> call = service.getGeo(userId);
        call.enqueue(new Callback <User> () {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User user = response.body();
                    listener.onSuccess(user);
                } else {
                    listener.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure();
            }
        });
    }


    @Override
    public void onUpdateFeedById(int id, Feed feed, final OnFinishedListener listener) {
        FeedService feedService = GeneratorService.createService(FeedService.class,
                Session.username, Session.APIKey);

        final Feed feedObj = feed;
        feedObj.setId(id);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(feedObj);
            }
        });

        Call<ResponseBody> call = feedService.updateFeed(id, feed.toJsonObject());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("+++++++++android: ", "success" + response.code());
                listener.onSuccess(null);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("+++++++++android: ", "Call api: Failure");
                listener.onFailure();
            }

        });
    }

    @Override
    public void onCreatedFeed(Map<String, RequestBody> map, Feed feed, final OnFinishedListener listener) {
        FeedService feedService = GeneratorService.createService(FeedService.class,
                Session.username, Session.APIKey);

        RequestBody name = RequestBody.create(
                MediaType.parse("text/plain"), feed.getName());

        RequestBody rating = RequestBody.create(
                MediaType.parse("text/plain"), feed.getRating());

        RequestBody time = RequestBody.create(
                MediaType.parse("text/plain"), String.valueOf(feed.getTime()));

        RequestBody foodTypeId = RequestBody.create(
                MediaType.parse("text/plain"), String.valueOf(feed.getFoodTypeId()));

        Call<ResponseBody> call = feedService.createFeed(map, name, rating, time, foodTypeId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    Log.d("+++++++++android: ", "success" + response.code());
                    listener.onSuccessCreate();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("+++++++++android: ", "Call api: Failure");
            }

        });
    }


}
