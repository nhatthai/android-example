package demo.nhatthai.cafegrapp.presenter;

import demo.nhatthai.cafegrapp.model.User;
import demo.nhatthai.cafegrapp.view.UserView;

/**
 * Created by nhatthai on 7/5/16.
 */
public class UserPresenterImpl implements UserPresenter, UserInteractor.OnFinishedListener{
    private UserView userView;
    private UserInteractor userInteractor;

    public UserPresenterImpl(UserView userView) {
        this.userView = userView;
        this.userInteractor = new UserInteractorImpl();

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccess(User user) {

    }
}
