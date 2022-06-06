package com.edutech.ui.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.edutech.Model.Request.SendOtpBody;
import com.edutech.Presenter.VerifyOtp_Presenter;
import com.edutech.R;
import com.edutech.SharedPerfence.MyPreferences;
import com.edutech.SharedPerfence.PrefConf;
import com.edutech.databinding.ActivityEnterOtpBinding;
import com.edutech.utils.AppUtils;
import com.irozon.sneaker.Sneaker;

import okhttp3.ResponseBody;

public class EnterOTP extends AppCompatActivity implements View.OnClickListener, VerifyOtp_Presenter.OTP_VerifyView {
    ActivityEnterOtpBinding binding;
    private Context context;
    private Dialog dialog;
    private VerifyOtp_Presenter presenter;
    private View view;
    String Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enter_otp);
        view = binding.getRoot();
        context = EnterOTP.this;
        dialog = AppUtils.hideShowProgress(context);

        presenter = new VerifyOtp_Presenter(this);

        Email = MyPreferences.getInstance(this).getString(PrefConf.EMAIL,null);

        binding.btnConfirm.setOnClickListener(this);
        binding.txtResend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                String otp = binding.enterOtp.getText().toString().trim();
                if (otp.isEmpty()){

                    Sneaker.with(this)
                            .setTitle("Please enter  Valid OTP !")
                            .setMessage("")
                            .setCornerRadius(4)
                            .setDuration(1500)
                            .sneakWarning();

                }else {
                    SendOtpBody user = new SendOtpBody(Email,otp);
                    presenter.VerifyUser( user);
                }
                break;

            case R.id.txt_resend:
                SendOtpBody user = new SendOtpBody(Email,null);
                presenter.SendOTP(context, user);
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
        if (message.equalsIgnoreCase("ok")){
            startActivity(new Intent(this, ChangePassword.class));

        }

    }

    @Override
    public void onSendOTPSuccess(ResponseBody responseBody, String message) {
        if (message.equalsIgnoreCase("ok")){
            Sneaker.with(this)
                    .setTitle("Message")
                    .setMessage("OTP sent to your registered email. Please check your Inbox and Spam folder as well")
                    .setCornerRadius(4)
                    .setDuration(1500)
                    .sneakSuccess();
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