package com.edutech.ui.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.edutech.Model.Request.LoginBody;
import com.edutech.Model.Response.TeacherResponse;
import com.edutech.Presenter.ChangePasswordPresenter;
import com.edutech.Presenter.TeacherLoginPresenter;
import com.edutech.R;
import com.edutech.SharedPerfence.MyPreferences;
import com.edutech.SharedPerfence.PrefConf;
import com.edutech.databinding.ActivityChangePasswordBinding;
import com.edutech.databinding.ActivityLoginBinding;
import com.edutech.utils.AppUtils;
import com.irozon.sneaker.Sneaker;

import okhttp3.ResponseBody;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener, ChangePasswordPresenter.ChangePasswordView {
    ActivityChangePasswordBinding binding;
    private Context context;
    private Dialog dialog;
    private ChangePasswordPresenter presenter;
    private View view;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_change_password);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);

        view = binding.getRoot();
        context = ChangePassword.this;
        presenter = new ChangePasswordPresenter(this);
        dialog = AppUtils.hideShowProgress(context);

        Email = MyPreferences.getInstance(this).getString(PrefConf.EMAIL,null);


        binding.btnChange.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change:
                String pass = binding.edPass.getText().toString().trim();
                String Cpass = binding.edCpass.getText().toString().trim();
                if (pass.isEmpty() || Cpass.isEmpty()){
                    Sneaker.with(this)
                            .setTitle("Please Enter New & Confirm password")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakWarning();
                }else if (!Cpass.equalsIgnoreCase(pass)){
                    Sneaker.with(this)
                            .setTitle("please check Confirm password ")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakError();
                }else {
                    LoginBody body = new LoginBody(Email,pass);
                    presenter.ChangePassword(context,body);
                }
                break;
        }
    }

    @Override
    public void showHideProgress(boolean isShow) {
        if (isShow) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {
        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }

    @Override
    public void onChangePasswordSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")){
            startActivity(new Intent(ChangePassword.this,LoginActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
            MyPreferences.getInstance(context).clearPreferences();
            Toast.makeText(this, "Password Change Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .setCornerRadius(4)
                .setDuration(1500)
                .sneakError();
    }
}