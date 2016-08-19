package demo.nhatthai.cafegrapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.activity.CreateFeedActivity;
import demo.nhatthai.cafegrapp.activity.UserActivity;
import demo.nhatthai.cafegrapp.adapter.ProfileFeedAdapter;
import demo.nhatthai.cafegrapp.events.RecyclerViewScrollListener;
import demo.nhatthai.cafegrapp.model.Feed;
import demo.nhatthai.cafegrapp.model.FoodType;
import demo.nhatthai.cafegrapp.model.User;
import demo.nhatthai.cafegrapp.presenter.FeedPresenter;
import demo.nhatthai.cafegrapp.presenter.FeedPresenterImpl;
import demo.nhatthai.cafegrapp.settings.Constant;
import demo.nhatthai.cafegrapp.settings.Session;
import demo.nhatthai.cafegrapp.view.FeedView;
import demo.nhatthai.cafegrapp.view.ProfileView;


/**
 * Created by nhatthai on 5/20/16.
 */
public class ProfileFragment extends BaseFragment
        implements ProfileView, FeedView, ProfileFeedAdapter.OnFeedItemClickListener{

    private ArrayList<Feed> feedList;
    private TextView mTxtRecipes, mTxtFollowing, mTxtFollowers, mTxtAddress, mTxtNameProfile;
    private ProfileFeedAdapter profileFeedAdapter;
    private FeedPresenter feedPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        String username = "";
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Context context = getContext();

        ImageView img_profile = (ImageView) view.findViewById(R.id.img_profile);
        mTxtNameProfile = (TextView) view.findViewById(R.id.txt_name_profile);
        mTxtAddress = (TextView) view.findViewById(R.id.txt_address);
        mTxtRecipes = (TextView) view.findViewById(R.id.txt_recipes);
        mTxtFollowing = (TextView) view.findViewById(R.id.txt_following);
        mTxtFollowers = (TextView) view.findViewById(R.id.txt_followers);

        onLoadUserData();

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getString("text_name") != null) {
                mTxtNameProfile.setText(bundle.getString("text_name"));
            }

            if (bundle.getInt("img_url") != 0) {
                img_profile.setTag(R.id.img_profile, bundle.getInt("img_url"));
            }

        } else {
            Intent intent = getActivity().getIntent();
            username = intent.getStringExtra("username");

            if (intent.getStringExtra("imgurl") != null) {
                Glide.with(context).load(intent.getStringExtra("imgurl"))
                        .centerCrop().into(img_profile);
            }
        }

        feedList = new ArrayList<Feed>();

//        ProfilePresenter profilePresenter = new ProfilePresenterImpl(this);
//        profilePresenter.getProfile(username);

        feedPresenter = new FeedPresenterImpl(this);
        feedPresenter.onFeedByUser();

        //initAdapter
        if (profileFeedAdapter == null) {
            profileFeedAdapter = new ProfileFeedAdapter(context, feedList);
            profileFeedAdapter.setOnFeedItemClickListener(this);
        }

        //init list data
        RecyclerView rvProfileFeeds = (RecyclerView) view.findViewById(R.id.rvProfileFeeds);
        LinearLayoutManager layoutManager = new FeedFragment.LinearLayoutManagerExt(getActivity());
        rvProfileFeeds.setLayoutManager(layoutManager);
        rvProfileFeeds.setHasFixedSize(true);
        rvProfileFeeds.setAdapter(profileFeedAdapter);

        // add scroll listener
        rvProfileFeeds.addOnScrollListener(new RecyclerViewScrollListener(layoutManager, 2) {
            @Override
            public void onLoadMore() {
                onLoadMoreFeedByUser();
            }

            @Override
            public int getFooterViewType() {
                if (feedList.size() > 0) {
                    return feedList.get(feedList.size() - 1) != null ? 0 : 1;
                } else {
                    return 0;
                }
            }
        });

        mTxtNameProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoadGrappUser();
            }
        });

        return view;
    }

    private void onLoadUserData() {
        mTxtRecipes.setText(Session.recipes);
        mTxtFollowing.setText(Session.following);
        mTxtFollowers.setText(Session.follower);
        mTxtAddress.setText(Session.city + ", " + Session.country);
        mTxtNameProfile.setText(Session.firstname + " " + Session.lastname);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadFoodType(List<FoodType> foodTypes) {

    }

    @Override
    public void onLoadFeed(Feed feed) {

    }

    @Override
    public void onLoadProfile(User user) {
        mTxtAddress.setText(user.getLocation());
        mTxtFollowers.setText(String.valueOf(user.getFollowers()));
        mTxtFollowing.setText(String.valueOf(user.getFollowing()));
        mTxtRecipes.setText(String.valueOf(user.getRecipes()));
        mTxtNameProfile.setText(user.getName());
    }

    @Override
    public void onLoadFeeds(List<Feed> feeds) {
        feedList.clear();
        feedList.addAll(feeds);
        profileFeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess() {

    }

    public void onLoadMoreFeedByUser() {
        feedList.add(null);
        profileFeedAdapter.notifyItemInserted(feedList.size() - 1);

        //load more
        feedPresenter.onFeedMoreByUser();
    }

    @Override
    public void onFinishLoadMore(List<Feed> feeds) {
        //Remove loading item
        feedList.remove(feedList.size() - 1);
        profileFeedAdapter.notifyItemRemoved(feedList.size());

        // fetch data here
        int index = feedList.size();
        feedList.addAll(feeds);

        profileFeedAdapter.notifyItemRangeInserted(index, feedList.size() - index);
        profileFeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFeedImageClick(Feed feed) {
        Intent i = new Intent(getActivity(), CreateFeedActivity.class);
        Bundle b = new Bundle();
        b.putInt("feedId", feed.getId());
        i.putExtras(b);
        startActivityForResult(i, Constant.updatedFeed);
    }

    private void onLoadGrappUser() {
        Intent i = new Intent(getActivity(), UserActivity.class);
        startActivityForResult(i, Constant.updatedUser);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Constant.updated) {
            if (requestCode == Constant.updatedUser) {
                onLoadUserData();
            }
            if (requestCode == Constant.updatedFeed){
                feedPresenter.onFeedByUser();
            }
        }
    }

}
