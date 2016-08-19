package demo.nhatthai.cafegrapp.activity;

import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import demo.nhatthai.cafegrapp.R;

/**
 * Created by nhatthai on 5/19/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        // ensure toolbar acts as action bar
        setSupportActionBar(tb);
    }

    // pass context to Calligraphy
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    /**
     * Show the action bar
     * @param isShow - Show status
     */
    public void showActionBar(boolean isShow) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (isShow) {
                actionBar.show();
                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
                //actionBar.setDisplayShowTitleEnabled(false);
            } else {
                actionBar.hide();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
