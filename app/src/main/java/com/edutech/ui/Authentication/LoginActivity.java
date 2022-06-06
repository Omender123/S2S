package com.edutech.ui.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.edutech.Model.Request.LoginBody;
import com.edutech.Model.Response.TeacherResponse;
import com.edutech.Presenter.TeacherLoginPresenter;
import com.edutech.R;
import com.edutech.databinding.ActivityLoginBinding;
import com.edutech.utils.AppUtils;
import com.edutech.utils.Validation;
import com.irozon.sneaker.Sneaker;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TeacherLoginPresenter.TeacherLoginView {
    ActivityLoginBinding binding;
    private Context context;
    private Dialog dialog;
    private TeacherLoginPresenter presenter;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_login);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        view = binding.getRoot();
        context = LoginActivity.this;
        presenter = new TeacherLoginPresenter(this);
        dialog = AppUtils.hideShowProgress(context);

        binding.txtForget.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.txt_forget:
                startActivity(new Intent(this, ResetPassword.class));
                break;

            case R.id.btn_login:
                doLogin();
                break;

        }
    }

    private void doLogin() {
        String Email = binding.edGmail.getText().toString().trim();
        String password = binding.edPass.getText().toString().trim();
        if (Email.isEmpty() && !Validation.isValidEmail(Email)) {
            binding.edGmail.requestFocus();

            Sneaker.with(this)
                    .setTitle("Please enter  Valid Email !")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakWarning();


        } else if (password.isEmpty()) {
            binding.edPass.requestFocus();
            Sneaker.with(this)
                    .setTitle("Please enter Password !")
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakWarning();

        } else {
            // AppUtils.FullScreen(this);

            LoginBody user = new LoginBody(Email, password);
            presenter.TeacherLogin(context,user);
        }

    }


    @Override
    public void showHideLoginProgress(boolean isShow) {
        if (isShow) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }

    @Override
    public void onLoginError(String message) {
        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();

    }

    @Override
    public void onTeacherLoginSuccess(TeacherResponse response, String message) {
        if (message.equalsIgnoreCase("ok")){
            Sneaker.with(this)
                    .setTitle(response.getMessage())
                    .setMessage("")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();

            Log.d("Tokennnnn",response.getToken());
        }
    }

    @Override
    public void onLoginFailure(Throwable t) {
        Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();

    }
}