package demo.nhatthai.cafegrapp.view;

import java.util.List;

/**
 * Created by nhatthai on 5/12/16.
 */
public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<String> items);

    void showMessage(String message);
}