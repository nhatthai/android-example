package demo.nhatthai.cafegrapp.presenter;

import demo.nhatthai.cafegrapp.model.User;
import demo.nhatthai.cafegrapp.service.UserService;
import demo.nhatthai.cafegrapp.service.UserServiceImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nhatthai on 6/22/16.
 */
public class ProfileInteractorImpl implements ProfileInteractor {

    @Override
    public void onLoadProfile(String username, final OnFinishedListener listener) {
        UserService service = UserServiceImpl.getService();
        Call<User> call = service.getUser(username);
        call.enqueue(new Callback<User>() {
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
}
