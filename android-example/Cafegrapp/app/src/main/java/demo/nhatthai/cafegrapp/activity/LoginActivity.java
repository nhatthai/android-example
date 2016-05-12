package demo.nhatthai.cafegrapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;


import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.presenter.LoginPresenterImpl;
import demo.nhatthai.cafegrapp.view.LoginView;

/**
 * Created by nhatthai on 5/12/16.
 */
public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private EditText mEmail, mPassword;
    private LoginPresenterImpl mPresenter;
    private ProgressBar mProgressLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mProgressLogin = (ProgressBar) findViewById(R.id.progressLogin);
        mEmail = (EditText) findViewById(R.id.editTxtEmail);
        mPassword = (EditText) findViewById(R.id.editTxtPass);

        //register event click for Login Button
        findViewById(R.id.btnLogin).setOnClickListener(this);

        mPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        mPresenter.validateCredentials(mEmail.getText().toString(), mPassword.getText().toString());
    }

    @Override
    public void showProgress() {
        mProgressLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressLogin.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        mEmail.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        mPassword.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {
        Log.d("Android", "Navigate to home page");
        startActivity(new Intent(this, CafeGragActivity.class));
        finish();
    }
}
