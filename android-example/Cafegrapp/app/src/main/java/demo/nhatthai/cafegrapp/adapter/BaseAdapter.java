package demo.nhatthai.cafegrapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import com.bumptech.glide.Glide;

import demo.nhatthai.cafegrapp.R;

/**
 * Created by nhatthai on 5/20/16.
 */
public class BaseAdapter extends RecyclerView.Adapter<ViewHolder>  {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    // Views
    private TextView mTxtViewName, mTxtFeedName;
    ImageView mImgFeed, mImgLike, mImgProfile;

    // constructor
    ViewHolder(View itemView) {
        super(itemView);

        mTxtViewName = (TextView) itemView.findViewById(R.id.txtName);
        mImgFeed = (ImageView) itemView.findViewById(R.id.imgFeed);
        mTxtFeedName = (TextView) itemView.findViewById(R.id.txtFeedTitle);
        mImgLike = (ImageView) itemView.findViewById(R.id.imgLike);
        mImgProfile = (ImageView) itemView.findViewById(R.id.imgProfile);
    }

    // set data
    public void setData(String name, String username, boolean isLike) {
        mTxtViewName.setText(username);
        mTxtFeedName.setText(name);

        if (isLike) {
            mImgLike.setImageResource(R.drawable.ic_heart_red);
            mImgLike.setTag("heart_red");
        } else {
            mImgLike.setImageResource(R.drawable.ic_heart_border);
            mImgLike.setTag("heart_border");
        }
    }

    // set data
    public void setData(String name, String username, boolean isLike, String imgUrl, Context context) {
        Log.d("Android: ", imgUrl);
        Glide.with(context).load(R.drawable.images_3).centerCrop().into(mImgFeed);
        setData(name, username, isLike);
    }
}