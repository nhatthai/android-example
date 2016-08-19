package demo.nhatthai.cafegrapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nhatthai on 6/8/16.
 */
public class ValidateUtil {
    public static boolean isValidEmail(String email) {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
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
    public static boolean isValidPassword(String pass) {
        final String PASSWORD_PATTERN =
                "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

        if (pass != null && pass.length() > 6) {
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(pass);
            return matcher.matches();
        }
        return false;
    }
}
