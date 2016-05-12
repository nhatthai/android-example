package demo.nhatthai.cafegrapp.presenter;

/**
 * Created by nhatthai on 5/12/16.
 */
public interface LoginInteractor {

    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }

    void login(String username, String password, OnLoginFinishedListener listener);

}