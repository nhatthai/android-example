package demo.nhatthai.cafegrapp.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.AdapterView;
import android.widget.Toast;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.view.MainView;
import demo.nhatthai.cafegrapp.presenter.MainPresenterImpl;
import demo.nhatthai.cafegrapp.presenter.MainPresenter;


/**
 * Created by nhatthai on 5/12/16.
 */
public class CafeGragActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener{

    private ListView mListView;
    private ProgressBar mProgressBar;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list);
        mListView.setOnItemClickListener(this);
        mProgressBar = (ProgressBar) findViewById(R.id.progressLoadData);
        mPresenter = new MainPresenterImpl(this);
    }

    //=======implement Activity=============
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //=======implement from Mainview and itemclick=============
    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<String> items) {
        //set items into Adapter
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPresenter.onItemClicked(position);
    }
}
