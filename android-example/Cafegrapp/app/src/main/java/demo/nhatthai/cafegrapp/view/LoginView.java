package demo.nhatthai.cafegrapp.view;

/**
 * Created by nhatthai on 5/12/16.
 */
public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}