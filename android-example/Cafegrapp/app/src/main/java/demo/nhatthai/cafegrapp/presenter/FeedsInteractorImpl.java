package demo.nhatthai.cafegrapp.presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import demo.nhatthai.cafegrapp.service.FeedService;
import demo.nhatthai.cafegrapp.service.GeneratorService;
import demo.nhatthai.cafegrapp.settings.Session;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import demo.nhatthai.cafegrapp.model.Feed;


/**
 * Created by nhatthai on 5/12/16.
 */
public class FeedsInteractorImpl implements FeedsInteractor {

    final String imgURLs[] = {
            "http://www.localfoodtalks.co.uk/wp-content/uploads/2015/09/foodworld.jpg",
            "http://reachingutopia.com/wp-content/uploads/2014/04/healthy-wraps.jpg",
            "http://www.conagrafoods.ca/images/pam-background.jpg",
            "http://reachingutopia.com/wp-content/uploads/2014/04/healthy-wraps.jpg",
            "http://las-vegas.eat24hours.com/files/cuisines/v4/healthy-foods.jpg",
            "http://reachingutopia.com/wp-content/uploads/2014/04/healthy-wraps.jpg",
            "http://las-vegas.eat24hours.com/files/cuisines/v4/healthy-foods.jpg",
            "http://www.conagrafoods.ca/images/bg1.jpg",
            "http://las-vegas.eat24hours.com/files/cuisines/v4/healthy-foods.jpg"};

    private int limit = 4, offset = 0, totalCount = 0;
    private String username;

    @Override public void getResponseItems(final OnFinishedListener listener) {
        FeedService service = GeneratorService.createService(
                FeedService.class, Session.username, Session.APIKey);

        offset = 0;

        Call<ResponseBody> call = service.getFeeds();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    onProcessResponse(response, listener, false);
                } else {
                    listener.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

    @Override
    public void getResponseFeedsLoadMore(final OnFinishedListener listener) {
        FeedService service = GeneratorService.createService(
                FeedService.class, Session.username, Session.APIKey);

        if (offset <= totalCount) {
            Call<ResponseBody> call = service.getFeeds(limit, offset);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        onProcessResponse(response, listener, true);
                    } else {
                        listener.onResponseError();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listener.onFailure();
                }
            });
        } else {
            Log.d("Offset request", " "+ offset);
            Log.d("Total Count request", " "+ totalCount);
        }
    }


    @Override
    public void getResponseFeedsByUser(final OnFinishedListener listener) {
        FeedService service = GeneratorService.createService(
                FeedService.class, Session.username, Session.APIKey);

        offset = 0;
        username = Session.username;

        Call<ResponseBody> call = service.getFeedsByUser();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    onProcessResponse(response, listener, false);
                } else {
                    listener.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

    @Override
    public void getFeedsLoadMoreByUser(final OnFinishedListener listener) {
        username = Session.username;

        FeedService service = GeneratorService.createService(
                FeedService.class, Session.username, Session.APIKey);

        if (offset <= totalCount) {
            Call<ResponseBody> call = service.getFeedsByUser(limit, offset);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        onProcessResponse(response, listener, true);
                    } else {
                        listener.onResponseError();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listener.onFailure();
                }
            });
        } else {
            Log.d("Offset request", " "+ offset);
            Log.d("Total Count request", " "+ totalCount);
        }
    }

    @Override
    public void getFeedById(final OnFinishedListener listener, int feedId) {
        FeedService service = GeneratorService.createService(
                FeedService.class, Session.username, Session.APIKey);

        Call<Feed> call = service.getFeedById(feedId);
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.code() == 200) {
                    Feed feed =  (Feed) response.body();
                    listener.onResponseFinished(feed);
                    listener.onSuccess();
                } else {
                    listener.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

    /**
     * Process response data for Getting Feeds
     * @param response
     * @param listener
     * @param isLoadMore
     */
    private void onProcessResponse(Response<ResponseBody> response,
                                   final OnFinishedListener listener, boolean isLoadMore) {
        try {
            Gson gson = new Gson();
            String data = response.body().string();
            JsonElement json = gson.fromJson(data, JsonElement.class);
            JsonArray feeds= json.getAsJsonObject().get("objects").getAsJsonArray();

            offset = json.getAsJsonObject().get("meta").getAsJsonObject()
                    .get("offset").getAsInt() + json.getAsJsonObject().get("meta").getAsJsonObject()
                    .get("limit").getAsInt();
            totalCount = json.getAsJsonObject().get("meta").getAsJsonObject()
                    .get("total_count").getAsInt();

            // Serializer
            List<Feed> feedList =  new ArrayList<Feed>();
            for (int i = 0, size = feeds.size(); i < size; i++) {
                JsonObject obj = feeds.get(i).getAsJsonObject();

                String foodType = "";

                try {
                    if (obj.get("food_type") != null && obj.get("food_type")
                            .getAsJsonObject().get("name") != null) {
                        foodType = obj.get("food_type")
                                .getAsJsonObject().get("name").getAsString();
                    }
                } catch (Exception e) {
                }

                try {
                    username = obj.get("user").getAsJsonObject()
                            .get("username").getAsString();
                } catch (Exception e) {
                }

                String name = obj.get("name").getAsString();
                String rating = obj.get("rating").getAsString();
                int time = obj.get("time").getAsInt();
                int id = obj.get("id").getAsInt();

                Feed feed = new Feed(id, name, "", username, foodType, rating, time);
                feed.setImageUrl(imgURLs[i%4]);
                feedList.add(feed);
            }

            listener.onSuccess();

            if (isLoadMore) {
                listener.onFinishedLoadMore(feedList);
            } else {
                listener.onReponseFinished(feedList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}