package demo.nhatthai.cafegrapp.presenter;

import java.util.List;
/**
 * Created by nhatthai on 5/12/16.
 */

public interface FindItemsInteractor {

    interface OnFinishedListener {
        void onFinished(List<String> items);
    }

    void findItems(OnFinishedListener listener);
}