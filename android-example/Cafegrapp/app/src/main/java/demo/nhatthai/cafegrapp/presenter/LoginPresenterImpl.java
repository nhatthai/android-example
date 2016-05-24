package demo.nhatthai.cafegrapp.presenter;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import demo.nhatthai.cafegrapp.view.LoginView;

/**
 * Created by nhatthai on 5/12/16.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        login(username, password);
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }

    }
    @Override
    public void onSuccess() {
        if (loginView != null) {
            Log.d("Android", "Implement Navigation to Home");
            loginView.navigateToHome();
        }
    }

    @Override
    public void login(final String username, final String password) {
        // Mock login. I'm creating a handler to delay the answer a couple of seconds
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean isError = false;

                // is empty or not valid email then has error
                if (TextUtils.isEmpty(username) || !isValidEmail(username)){
                    onUsernameError();
                    isError = true;
                }

                // is empty or not valid pass then has error
                if (TextUtils.isEmpty(password) || !isValidPassword(password)){
                    onPasswordError();
                    isError = true;
                }

                //not error this mean is success
                if (!isError){
                    Log.d("Android", "Call success");
                    onSuccess();
                }
            }
        }, 2000);
    }

    /**
     * validating email id
     * @param email
     * @return
     */
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * validating password with retype password
     * @param pass
     * @return
     */
    private boolean isValidPassword(String pass) {
        String PASSWORD_PATTERN =
                "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

        if (pass != null && pass.length() > 6) {
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(pass);
            return matcher.matches();
        }
        return false;
    }
}
