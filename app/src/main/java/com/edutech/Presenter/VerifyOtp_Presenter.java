package com.edutech.Presenter;

import android.content.Context;

import com.edutech.Model.Request.SendOtpBody;
import com.edutech.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtp_Presenter {

    private OTP_VerifyView view;

    public VerifyOtp_Presenter(OTP_VerifyView view) {
        this.view = view;
    }

    public void VerifyUser(SendOtpBody body) {
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.S2SApi((Context) view).VerifyOTP(body);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onVerifyUserSuccess(response.message());
                } else if (response.code() == 400) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("error");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });


    }

    public void SendOTP(Context context, SendOtpBody body) {
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.S2SApi(context).SendOtp(body);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onSendOTPSuccess(response.body(), response.message());
                } else if (response.code() == 400) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("error");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    view.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }


    public interface OTP_VerifyView {
        void showHideProgress(boolean isShow);

        void onError(String message);

        void onVerifyUserSuccess(String message);

        void onSendOTPSuccess(ResponseBody responseBody, String message);


        void onFailure(Throwable t);
    }
}
