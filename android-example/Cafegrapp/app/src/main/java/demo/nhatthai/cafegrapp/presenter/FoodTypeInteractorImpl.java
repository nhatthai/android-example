package demo.nhatthai.cafegrapp.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import demo.nhatthai.cafegrapp.model.FoodType;
import demo.nhatthai.cafegrapp.service.FoodTypeService;
import demo.nhatthai.cafegrapp.service.GeneratorService;
import demo.nhatthai.cafegrapp.settings.Session;
import demo.nhatthai.cafegrapp.view.FeedView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nhatthai on 6/29/16.
 */
public class FoodTypeInteractorImpl implements FoodTypeInteractor {

    private FeedView feedView;

    public FoodTypeInteractorImpl(FeedView feedView) {
        this.feedView = feedView;
    }

    private void onResponseFinished(List<FoodType> foodTypeList) {
        feedView.onLoadFoodType(foodTypeList);
    }

    private void onResponseError() {

    }

    @Override
    public void getFoodType() {
        FoodTypeService service = GeneratorService.createService(FoodTypeService.class,
                Session.username, Session.APIKey);
        Call<ResponseBody> call = service.getFoodType();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    Gson gson = new Gson();
                    String data = null;
                    try {
                        data = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JsonElement json = gson.fromJson(data, JsonElement.class);
                    JsonArray foodTypes = json.getAsJsonObject().get("objects").getAsJsonArray();

                    List<FoodType> foodTypeList = new ArrayList<FoodType>();

                    for (int i = 0, size = foodTypes.size(); i < size; i++) {
                        JsonObject obj = foodTypes.get(i).getAsJsonObject();
                        FoodType foodType = new FoodType(
                                obj.get("id").getAsInt(), obj.get("name").getAsString());
                        foodTypeList.add(foodType);
                    }

                    onResponseFinished(foodTypeList);

                } else {
                    Log.d("+++++++++android: ", "Handle error:"+ response.code());
                    onResponseError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("+++++++++android: ", "Call api: Failure");
            }
        });
    }
}
