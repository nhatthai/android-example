package demo.nhatthai.cafegrapp.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by nhatthai on 6/29/16.
 */
public interface FoodTypeService {
    @GET("food_type")
    Call<ResponseBody> getFoodType();
}
