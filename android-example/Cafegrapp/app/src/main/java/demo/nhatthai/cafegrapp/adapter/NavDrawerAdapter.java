package demo.nhatthai.cafegrapp.adapter;

import android.util.Log;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import demo.nhatthai.cafegrapp.R;

/**
 * Created by nhatthai on 5/20/16.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<MenuItemHolder> {
    String TITLES[] = {"FEED", "EXPLORE", "CUISINES", "MESSAGES", "VISIT THE CAFES"};
    int ICONS[] = {R.drawable.ic_feed_48,
            R.drawable.ic_share_48,
            R.drawable.ic_globe_48,
            R.drawable.ic_email_white_48,
            R.drawable.ic_target_48};

    //create adapter
    private LayoutInflater mLayoutInflater;
    private String mNavTitles[];
    private int mIcons[];
    private OnNavItemClickListener mListener;

    public NavDrawerAdapter() {
        mNavTitles = TITLES;
        mIcons = ICONS;
    }

    public void setOnNavItemClickListener(OnNavItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public MenuItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new MenuItemHolder(mLayoutInflater.inflate(R.layout.menu_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(MenuItemHolder viewHolder, final int position) {
        final String name = mNavTitles[position];
        final int imageResId = mIcons[position];
        viewHolder.setData(imageResId, name);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Android", "click nav item"+ position);
                mListener.onNavItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length;
    }

    /**
     *
     */
    public interface OnNavItemClickListener {
        void onNavItemClick(int position);
    }

}

//
class MenuItemHolder extends RecyclerView.ViewHolder {
    // Views
    private TextView mNameTextView;
    private ImageView mImgView;

    // constructor
    public MenuItemHolder(View itemView) {
        super(itemView);
        mNameTextView = (TextView) itemView.findViewById(R.id.txtTitle);
        mImgView = (ImageView) itemView.findViewById(R.id.imgView);
    }

    // set data
    public void setData(int imageResId, String name) {
        mNameTextView.setText(name);
        mImgView.setImageResource(imageResId);
    }
}