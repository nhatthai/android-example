package demo.nhatthai.cafegrapp.service;

import com.google.gson.JsonObject;

import java.util.Map;

import demo.nhatthai.cafegrapp.model.Feed;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by nhatthai on 6/29/16.
 */
public interface FeedService {

    @GET("feed")
    Call<ResponseBody> getFeeds();

    @GET("feed/user")
    Call<ResponseBody> getFeedsByUser();

    @GET("feed/user")
    Call<ResponseBody> getFeedsByUser(@Query("limit") int limit, @Query("offset") int offset);

    @GET("feed")
    Call<ResponseBody> getFeeds(@Query("limit") int limit, @Query("offset") int offset);

    @GET("feed/{id}")
    Call<Feed> getFeedById(@Path("id") int id);

    @PUT("feed/{id}")
    Call<ResponseBody> updateFeed(@Path("id") int id, @Body JsonObject jsonObject);

//    @POST("feed")
//    Call<ResponseBody> createFeed(@Body JsonObject jsonObject);

    @Multipart
    @POST("feed")
    Call<ResponseBody> createFeed(@PartMap Map<String, RequestBody> params,
                                  @Part("name") RequestBody name,
                                  @Part("rating") RequestBody rating,
                                  @Part("time") RequestBody time,
                                  @Part("food_type_id") RequestBody food_type_id);
}