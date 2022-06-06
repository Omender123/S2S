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
import com.edutech.Model.Request.SendOtpBody;
import com.edutech.Presenter.TeacherLoginPresenter;
import com.edutech.Presenter.VerifyOtp_Presenter;
import com.edutech.R;
import com.edutech.SharedPerfence.MyPreferences;
import com.edutech.SharedPerfence.PrefConf;
import com.edutech.databinding.ActivityLoginBinding;
import com.edutech.databinding.ActivityResetPasswordBinding;
import com.edutech.utils.AppUtils;
import com.edutech.utils.Validation;
import com.irozon.sneaker.Sneaker;

import okhttp3.ResponseBody;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener, VerifyOtp_Presenter.OTP_VerifyView {
    ActivityResetPasswordBinding binding;
    private Context context;
    private Dialog dialog;
    private VerifyOtp_Presenter presenter;
    private View view;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_reset_password);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        view = binding.getRoot();
        context = ResetPassword.this;
        dialog = AppUtils.hideShowProgress(context);


        presenter = new VerifyOtp_Presenter(this);


        binding.btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_send:

                //  startActivity(new Intent(this, EnterOTP.class));
                 Email = binding.edGmail.getText().toString().trim();
                if (Email.isEmpty() && !Validation.isValidEmail(Email)) {
                    binding.edGmail.requestFocus();

                    Sneaker.with(this)
                            .setTitle("Please enter  Valid Email !")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakWarning();


                } else {
                    // AppUtils.FullScreen(this);

                    SendOtpBody user = new SendOtpBody(Email,null);
                    presenter.SendOTP(context, user);
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
    public void onVerifyUserSuccess(String message) {

    }

    @Override
    public void onSendOTPSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")) {

            Toast.makeText(this, "OTP sent to your registered email. Please check your Inbox and Spam folder as well", Toast.LENGTH_LONG).show();
            MyPreferences.getInstance(this).putString(PrefConf.EMAIL,Email);
            startActivity(new Intent(this, EnterOTP.class));

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