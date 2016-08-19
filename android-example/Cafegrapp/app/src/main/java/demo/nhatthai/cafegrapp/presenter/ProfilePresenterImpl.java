package demo.nhatthai.cafegrapp.presenter;

import android.util.Log;

import com.google.gson.Gson;

import demo.nhatthai.cafegrapp.model.User;
import demo.nhatthai.cafegrapp.service.UserServiceImpl;
import demo.nhatthai.cafegrapp.service.UserService;
import demo.nhatthai.cafegrapp.view.ProfileView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nhatthai on 6/20/16.
 */
public class ProfilePresenterImpl implements ProfilePresenter, ProfileInteractor.OnFinishedListener {
    private ProfileView profileView;
    private ProfileInteractor profileInteractor;

    public ProfilePresenterImpl(ProfileView profileView) {
        this.profileView = profileView;
        profileInteractor = new ProfileInteractorImpl();

    }

    @Override
    public void getProfile(String username) {
        profileInteractor.onLoadProfile(username, this);
    }

    @Override
    public void onSuccess(User user) {
        profileView.onLoadProfile(user);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onResponseError() {

    }
}
