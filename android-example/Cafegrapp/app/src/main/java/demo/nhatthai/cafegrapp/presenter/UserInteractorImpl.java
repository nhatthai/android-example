package demo.nhatthai.cafegrapp.presenter;

import com.google.gson.JsonObject;

import demo.nhatthai.cafegrapp.model.User;
import demo.nhatthai.cafegrapp.service.GeneratorService;
import demo.nhatthai.cafegrapp.service.UserService;
import demo.nhatthai.cafegrapp.settings.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nhatthai on 7/5/16.
 */
public class UserInteractorImpl implements UserInteractor {

    @Override
    public void updateUser(int id, User user, final OnFinishedListener listener) {

        UserService service = GeneratorService.createService(
                UserService.class, Session.username, Session.APIKey);

//        JsonObject obj = new JsonObject();
//        obj.addProperty("first_name", first_name);
//        obj.addProperty("last_name", last_name);
//        obj.addProperty("email", email);
//        obj.addProperty("city", city);

        Call<User> call = service.updateGrappUser(id, user.toJsonObject());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User user = response.body();
                    listener.onSuccess(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

}
