package demo.nhatthai.cafegrapp.presenter;

import java.util.List;
import demo.nhatthai.cafegrapp.model.Feed;
import demo.nhatthai.cafegrapp.view.FeedView;


/**
 * Created by nhatthai on 5/12/16.
 */
public class FeedPresenterImpl implements FeedPresenter, FindItemsInteractor.OnFinishedListener {
    private FeedView feedView;
    private FindItemsInteractor findItemsInteractor;

    public FeedPresenterImpl(FeedView feedView) {
        this.feedView = feedView;
        findItemsInteractor = new FindItemsInteractorImpl();
    }

    @Override
    public void onResume() {
        if (feedView != null) {
            feedView.showProgress();
        }

        findItemsInteractor.findItems(this);
    }

    @Override
    public void onFeedFinished(List<Feed> feeds) {
        if (feedView != null) {
            feedView.setFeedsFragment(feeds);
            feedView.hideProgress();
        }
    }

}
