package demo.nhatthai.cafegrapp.service;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Part;
import retrofit2.http.Multipart;

import demo.nhatthai.cafegrapp.model.Item;
import demo.nhatthai.cafegrapp.model.User;

/**
 * Created by nhatthai on 6/20/16.
 */
public interface UserService {
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("users")
    Call<List<Item>> getItems();

    @GET("users")
    Call<List<User>> getUsers();

    @Multipart
    @PUT("user/photo")
    Call<User> updateUser(
            @Part("photo") RequestBody photo, @Part("description") RequestBody description);

    @PUT("grappuser/{id}")
    Call<User> updateGrappUser(@Path("id") int id, @Body JsonObject object);
}

