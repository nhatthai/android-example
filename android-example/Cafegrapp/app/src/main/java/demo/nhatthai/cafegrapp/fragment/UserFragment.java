package demo.nhatthai.cafegrapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.events.MessageEvent;
import demo.nhatthai.cafegrapp.model.User;
import demo.nhatthai.cafegrapp.presenter.UserInteractor;
import demo.nhatthai.cafegrapp.presenter.UserInteractorImpl;
import demo.nhatthai.cafegrapp.settings.Constant;
import demo.nhatthai.cafegrapp.settings.Session;
import demo.nhatthai.cafegrapp.view.UserView;

/**
 * Created by nhatthai on 7/5/16.
 */
public class UserFragment extends BaseFragment
        implements UserView, UserInteractor.OnFinishedListener {

    private Button mBtnSave, mBtnCancel;
    private EditText mEditFistName, mEditLastName, mEditEmail, mEditCity;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        mEditFistName = (EditText) view.findViewById(R.id.edit_txt_first_name);
        mEditLastName = (EditText) view.findViewById(R.id.edit_txt_last_name);
        mEditEmail = (EditText) view.findViewById(R.id.edit_txt_email);
        mEditCity = (EditText) view.findViewById(R.id.edit_txt_city);

        onLoadDataUser();

        mBtnSave = (Button) view.findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

        mBtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        return view;
    }

    private void onLoadDataUser() {
        mEditFistName.setText(Session.firstname);
        mEditLastName.setText(Session.lastname);
        mEditEmail.setText(Session.email);
        mEditCity.setText(Session.city);
    }

    private void updateUser() {
        UserInteractor interactor = new UserInteractorImpl();

        User user = new User(Session.grappUserId, mEditFistName.getText().toString(),
                mEditLastName.getText().toString(), mEditEmail.getText().toString(),
                mEditCity.getText().toString());

        interactor.updateUser(Session.grappUserId, user, this);
    }

    @Override
    public void onSuccess(User user) {
        Session.lastname = user.getLastName();
        Session.firstname = user.getFirstName();
        Session.city = user.getCity();
        Session.email = user.getEmail();

        //event change name in Drawer
        EventBus.getDefault().post(
                new MessageEvent(user.getFirstName(), user.getLastName(), user.getCity()));

        Intent i = getActivity().getIntent();
        getActivity().setResult(Constant.updated, i);
        getActivity().finish();
    }

    @Override
    public void onFailure() {

    }

    private void onBackPressed() {
        getActivity().onBackPressed();
    }
}
