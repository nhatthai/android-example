package demo.nhatthai.cafegrapp.view;

/**
 * Created by nhatthai on 5/12/16.
 */
public interface LoginView {

    void hideProgress();

    void showUsernameError();

    void showPasswordError();

    void navigateToHome();
}