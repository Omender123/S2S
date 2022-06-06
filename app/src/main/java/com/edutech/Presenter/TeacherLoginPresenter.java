package com.edutech.Presenter;

import android.content.Context;

import com.edutech.Model.Request.LoginBody;
import com.edutech.Model.Response.TeacherResponse;
import com.edutech.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherLoginPresenter {

    private TeacherLoginView view;

    public TeacherLoginPresenter(TeacherLoginView view) {
        this.view = view;
    }

    public void TeacherLogin(Context context , LoginBody body) {
        view.showHideLoginProgress(true);
        Call<TeacherResponse> loginCall = AppUtils.S2SApi((Context) view).TeacherLogin(body);

        loginCall.enqueue(new Callback<TeacherResponse>() {
            @Override
            public void onResponse(Call<TeacherResponse> call, Response<TeacherResponse> response) {
                view.showHideLoginProgress(false);

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {

                        view.onTeacherLoginSuccess(response.body(), response.message());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (response.code()==400) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("error");
                        view.onLoginError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<TeacherResponse> call, Throwable t) {
                view.showHideLoginProgress(false);
                view.onLoginFailure(t);
            }
        });

    }



    public  interface  TeacherLoginView{
        void showHideLoginProgress(boolean isShow);
        void onLoginError(String message);
        void onTeacherLoginSuccess(TeacherResponse response, String message);
        void onLoginFailure(Throwable t);
    }
}
