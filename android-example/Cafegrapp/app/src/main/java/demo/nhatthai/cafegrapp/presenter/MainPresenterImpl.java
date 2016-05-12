package demo.nhatthai.cafegrapp.presenter;

import java.util.List;
import demo.nhatthai.cafegrapp.view.MainView;

/**
 * Created by nhatthai on 5/12/16.
 */
public class MainPresenterImpl implements MainPresenter, FindItemsInteractor.OnFinishedListener {
    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        findItemsInteractor = new FindItemsInteractorImpl();
    }

    @Override
    public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }

        findItemsInteractor.findItems(this);
    }

    @Override
    // item click event
    public void onItemClicked(int position) {
        if (mainView != null) {
            mainView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    // call when finishing get data from InteractorImpl
    public void onFinished(List<String> items) {
        if (mainView != null) {
            //call CafeGragActivity
            mainView.setItems(items);
            mainView.hideProgress();
        }
    }
}
