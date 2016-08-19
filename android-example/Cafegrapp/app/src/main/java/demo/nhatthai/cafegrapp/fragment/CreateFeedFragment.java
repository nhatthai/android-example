package demo.nhatthai.cafegrapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.adapter.FoodTypeAdapter;
import demo.nhatthai.cafegrapp.model.Feed;
import demo.nhatthai.cafegrapp.model.FoodType;
import demo.nhatthai.cafegrapp.model.User;
import demo.nhatthai.cafegrapp.presenter.FeedDetailPresenter;
import demo.nhatthai.cafegrapp.presenter.FeedDetailPresenterImpl;
import demo.nhatthai.cafegrapp.presenter.FeedPresenter;
import demo.nhatthai.cafegrapp.presenter.FeedPresenterImpl;
import demo.nhatthai.cafegrapp.presenter.FoodTypeInteractor;
import demo.nhatthai.cafegrapp.presenter.FoodTypeInteractorImpl;
import demo.nhatthai.cafegrapp.settings.Constant;
import demo.nhatthai.cafegrapp.utils.RealPathUtil;

import demo.nhatthai.cafegrapp.view.FeedDetailView;
import demo.nhatthai.cafegrapp.view.FeedView;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Created by nhatthai on 6/29/16.
 */
public class CreateFeedFragment extends BaseFragment implements FeedView, FeedDetailView {
    private Spinner mSpinner;
    private Button mBtnPost, mBtnChooseImg;
    private int mFoodTypeId;
    private EditText mTxtEditName, mTxtEditTime, mTxtEditRating;
    private String mPathImg = "";
    private ImageView mImgView;
    private List<FoodType> foodTypeList;
    private int feedId;
    private Feed mFeed;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_create, container, false);

        Intent intent = getActivity().getIntent();
        feedId = intent.getIntExtra("feedId", 0);

        mImgView = (ImageView) view.findViewById(R.id.img_feed_view);
        mTxtEditName = (EditText) view.findViewById(R.id.txt_edit_title);
        mTxtEditTime = (EditText) view.findViewById(R.id.txt_edit_time);
        mTxtEditRating = (EditText) view.findViewById(R.id.txt_edit_rating);

        FoodTypeInteractor presenter = new FoodTypeInteractorImpl(this);
        presenter.getFoodType();

        // Spinner element
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        // Spinner click listener
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                FoodType item = (FoodType) parent.getItemAtPosition(position);
                mFoodTypeId = item.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBtnChooseImg = (Button) view.findViewById(R.id.btn_choose_img);
        mBtnChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalleryImg();
            }
        });

        mBtnPost = (Button) view.findViewById(R.id.btn_post);
        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postFeed();
            }
        });

        if (feedId > 0) {
            // load feed data if has feedId
            FeedPresenter feedPresenter = new FeedPresenterImpl(this);
            feedPresenter.onGetFeedById(feedId);
        }
        return view;
    }


    @Override
    public void onSuccess() {
        getActivity().onBackPressed();
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadFeeds(List<Feed> feeds) {

    }

    /**
     * Fill data into Element
     * @param feed
     */
    @Override
    public void onLoadFeed(Feed feed) {
        mTxtEditName.setText(feed.getName());
        mTxtEditTime.setText(String.valueOf(feed.getTime()));
        mTxtEditRating.setText(String.valueOf(feed.getRating()));

        mFeed = feed;

        if (foodTypeList != null && foodTypeList.size() > 0) {
            setSelectedSpinner(foodTypeList, mFeed.food_type.name);
        }
    }

    private int getIndex(List<FoodType> list, String name){
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public void onFinishLoadMore(List<Feed> feeds) {

    }

    @Override
    public void showProgress() {

    }

    /**
     * Load food type into dropdown list
     * @param foodTypes
     */
    @Override
    public void onLoadFoodType(List<FoodType> foodTypes) {
        foodTypeList = foodTypes;
        // Creating adapter for spinnerthera
        FoodTypeAdapter dataAdapter = new FoodTypeAdapter(
                getContext(), android.R.layout.simple_spinner_dropdown_item, foodTypes);
        mSpinner.setAdapter(dataAdapter);

        if (mFeed != null && mFeed.food_type.name != "") {
            setSelectedSpinner(foodTypeList, mFeed.food_type.name);
        }
    }

    private void setSelectedSpinner(List<FoodType> foodTypeList, String name) {
        //set Food Type
        mSpinner.setSelection(getIndex(foodTypeList, name));
    }

    public void openGalleryImg(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            Uri selectedImageUri = data.getData();
            Bitmap bitmap = null;

            try {
                bitmap = BitmapFactory.decodeStream(
                        getContext().getContentResolver().openInputStream(selectedImageUri));

                mPathImg = RealPathUtil.getRealPathFormURI(getContext(), selectedImageUri);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            mImgView.setImageBitmap(bitmap);
        }
    }

    /**
     * Post Feed item to server
     */
    public void postFeed() {
        FeedDetailPresenter feedDetailPresenter = new FeedDetailPresenterImpl(this);

        //get extend file name (.png, .jpg, .jpeg)
        Map<String, RequestBody> map = new HashMap<>();

        //File file = null;
        if (mPathImg != "") {
            File file = new File(mPathImg);
            //work upload file .jpg
            RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            map.put("file\"; filename=\"" + file.getName(), fbody);
        }

        if (feedId > 0) {
            Feed feed = new Feed(feedId, mTxtEditName.getText().toString(),
                    mTxtEditRating.getText().toString(),
                    Integer.parseInt(mTxtEditTime.getText().toString()),
                    mFoodTypeId);


            feedDetailPresenter.onFeedUpdate(feedId, feed);

        } else {
            Feed feed = new Feed(0, mTxtEditName.getText().toString(),
                    mTxtEditRating.getText().toString(),
                    Integer.parseInt(mTxtEditTime.getText().toString()),
                    mFoodTypeId);

            feedDetailPresenter.onCreateFeed(map, feed);

        }

    }

    /**
     * Reload Feed in ProfileFragment
     * @param user
     */
    @Override
    public void onLoadFeedDetail(User user) {
        Intent i = getActivity().getIntent();
        getActivity().setResult(Constant.updated, i);
        getActivity().finish();
    }

    /**
     *  Reload Feed in FeedFragment
     */
    @Override
    public void onLoadFeeds() {
        Intent i = getActivity().getIntent();
        getActivity().setResult(Constant.created, i);
        getActivity().finish();
    }
}
