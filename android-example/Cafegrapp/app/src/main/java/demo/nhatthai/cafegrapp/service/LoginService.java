package demo.nhatthai.cafegrapp.service;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by nhatthai on 6/23/16.
 */
public interface LoginService {

    @POST("user/login")
    Call<ResponseBody> doLogin(@Body HashMap<String, Object> obj);

    @POST("user/isauth")
    Call<ResponseBody> isAuth();

    @GET("therapist/{id}")
    Call<ResponseBody> getTherapist(@Path("id") int id);
}
