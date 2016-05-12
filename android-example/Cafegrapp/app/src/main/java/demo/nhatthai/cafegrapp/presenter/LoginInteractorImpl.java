package demo.nhatthai.cafegrapp.presenter;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import android.os.Handler;
import android.text.TextUtils;

/**
 * Created by nhatthai on 5/12/16.
 */
public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password,
                      final OnLoginFinishedListener listener) {
        // Mock login. I'm creating a handler to delay the answer a couple of seconds
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean isError = false;

                // is empty or not valid email then has error
                if (TextUtils.isEmpty(username) || !isValidEmail(username)){
                    listener.onUsernameError();
                    isError = true;
                }

                // is empty or not valid pass then has error
                if (TextUtils.isEmpty(password) || !isValidPassword(password)){
                    listener.onPasswordError();
                    isError = true;
                }

                //not error this mean is success
                if (!isError){
                    listener.onSuccess();
                }
            }
        }, 2000);
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
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
