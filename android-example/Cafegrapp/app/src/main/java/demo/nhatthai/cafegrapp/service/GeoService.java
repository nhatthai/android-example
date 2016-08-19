package demo.nhatthai.cafegrapp.service;

import demo.nhatthai.cafegrapp.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nhatthai on 6/21/16.
 */
public interface GeoService {
    @GET("users/{id}")
    Call<User> getGeo(@Path("id") int userId);
}
