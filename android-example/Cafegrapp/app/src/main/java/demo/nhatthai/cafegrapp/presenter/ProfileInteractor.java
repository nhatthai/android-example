package demo.nhatthai.cafegrapp.presenter;

import demo.nhatthai.cafegrapp.model.User;

/**
 * Created by nhatthai on 6/22/16.
 */
public interface ProfileInteractor {

    interface OnFinishedListener {
        void onSuccess(User user);

        void onFailure();

        void onResponseError();
    }

    void onLoadProfile(String username, final OnFinishedListener listener);
}
