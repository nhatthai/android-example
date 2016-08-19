package demo.nhatthai.cafegrapp.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.HashMap;

import demo.nhatthai.cafegrapp.service.GeneratorService;
import demo.nhatthai.cafegrapp.service.LoginService;
import demo.nhatthai.cafegrapp.settings.Session;
import demo.nhatthai.cafegrapp.view.LoginView;
import demo.nhatthai.cafegrapp.utils.ValidateUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nhatthai on 5/12/16.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    private void onUsernameError() {
        if (loginView != null) {
            loginView.showUsernameError();
            loginView.hideProgress();
        }
    }

    private void onPasswordError() {
        if (loginView != null) {
            loginView.showPasswordError();
            loginView.hideProgress();
        }

    }

    private void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }

    @Override
    public void login(final String username, final String password) {
        boolean isError = false;

        // is empty or not valid email then has error
        if (TextUtils.isEmpty(username) || !ValidateUtil.isValidEmail(username)){
            onUsernameError();
            isError = true;
        }

        // is empty or not valid pass then has error
//        if (TextUtils.isEmpty(password) || !ValidateUtil.isValidPassword(password)){
//            onPasswordError();
//            isError = true;
//        }

        if (!isError) {

            HashMap<String, Object> objMap = new HashMap<>();
            objMap.put("email", username);
            objMap.put("username", username);
            objMap.put("password", password);

            LoginService service =
                    GeneratorService.createService(LoginService.class, null, null);

            Call<ResponseBody> call = service.doLogin(objMap);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        try {
                            String data = response.body().string();
                            Gson gson = new Gson();
                            JsonElement json = gson.fromJson(data, JsonElement.class);

                            Session.grappUserId = json.getAsJsonObject().get("id")
                                    .getAsInt();
                            Session.username = json.getAsJsonObject().get("username")
                                    .getAsString();
                            Session.APIKey = json.getAsJsonObject().get("api_key")
                                    .getAsString();
                            Session.firstname = json.getAsJsonObject().get("first_name")
                                    .getAsString();
                            Session.lastname = json.getAsJsonObject().get("last_name")
                                    .getAsString();
                            Session.email = json.getAsJsonObject().get("email")
                                    .getAsString();
                            Session.city = json.getAsJsonObject().get("city")
                                    .getAsString();
                            Session.country = json.getAsJsonObject().get("country")
                                    .getAsString();
                            Session.recipes = json.getAsJsonObject().get("recipes")
                                    .getAsString();
                            Session.follower = json.getAsJsonObject().get("follower")
                                    .getAsString();
                            Session.following = json.getAsJsonObject().get("following")
                                    .getAsString();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        onSuccess();
                    } else {
                        Log.v("=======", "Error response: " + response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Login error:", t.getMessage());
                }
            });
        }
    }

}
