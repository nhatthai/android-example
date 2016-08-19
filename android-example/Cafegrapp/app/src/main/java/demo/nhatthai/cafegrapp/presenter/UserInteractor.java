package demo.nhatthai.cafegrapp.presenter;

import demo.nhatthai.cafegrapp.model.User;

/**
 * Created by nhatthai on 7/5/16.
 */
public interface UserInteractor {
    interface OnFinishedListener {
        void onSuccess(User user);

        void onFailure();
    }

    void updateUser(int id, User user, final OnFinishedListener listener);
}
