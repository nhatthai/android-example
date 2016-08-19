package demo.nhatthai.cafegrapp.fragment;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.model.User;
import demo.nhatthai.cafegrapp.presenter.FeedDetailPresenter;
import demo.nhatthai.cafegrapp.presenter.FeedDetailPresenterImpl;
import demo.nhatthai.cafegrapp.view.FeedDetailView;

/**
 * Created by nhatthai on 6/9/16.
 */
public class FeedDetailFragment extends BaseFragment implements FeedDetailView {

    private static final LatLng ORG_LATNG = new LatLng(16.0425, 108.214);

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_detail, container, false);
        Intent intent = getActivity().getIntent();

        TextView txtFeedTitle = (TextView) view.findViewById(R.id.txt_feed_title);
        txtFeedTitle.setText(intent.getStringExtra("title"));

        ImageView imgFeedItem = (ImageView) view.findViewById(R.id.img_feed);
        Glide.with(this.getActivity()).load(intent.getStringExtra("imgFeedUrl"))
                .centerCrop().into(imgFeedItem);

        int userId = intent.getIntExtra("userId", 0);

        FeedDetailPresenter feedDetailPresenter = new FeedDetailPresenterImpl(this);
        feedDetailPresenter.onLoadFeedDetail(userId);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        if (supportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            supportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, supportMapFragment).commit();
        }

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap gMap) {
                gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                gMap.getUiSettings().setMyLocationButtonEnabled(false);
                gMap.clear();

                gMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_point_small))
                        .position(ORG_LATNG));

                gMap.moveCamera(CameraUpdateFactory.newLatLng(ORG_LATNG));
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ORG_LATNG, 14));
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onLoadFeedDetail(User user) {
        Log.d("======", "call Feed detail api:" + user.address.geo.lat);
    }

    @Override
    public void onLoadFeeds() {

    }
}
