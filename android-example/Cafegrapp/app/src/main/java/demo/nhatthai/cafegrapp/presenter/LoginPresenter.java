package demo.nhatthai.cafegrapp.presenter;

/**
 * Created by nhatthai on 5/12/16.
 * Interface of Login Presenter Impl
 */
public interface LoginPresenter {
    void validateCredentials(String username, String password);
    void onDestroy();
}