package demo.nhatthai.cafegrapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.activity.MainActivity;
import demo.nhatthai.cafegrapp.presenter.LoginPresenterImpl;
import demo.nhatthai.cafegrapp.view.LoginView;


/**
 * Created by nhatthai on 5/19/16.
 */
public class LoginFragment extends BaseFragment implements LoginView, View.OnClickListener {

    private EditText mEmail, mPassword;
    private LoginPresenterImpl mPresenter;
    private ProgressBar mProgressLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mProgressLogin = (ProgressBar) view.findViewById(R.id.progressLogin);
        mEmail = (EditText) view.findViewById(R.id.editTxtEmail);
        mPassword = (EditText) view.findViewById(R.id.editTxtPass);

        //register event click for Login Button
        view.findViewById(R.id.btnLogin).setOnClickListener(this);

        mPresenter = new LoginPresenterImpl(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        Log.d("Android", "Click Login");
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
        Log.d("Android", "Navigation to Home");
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
