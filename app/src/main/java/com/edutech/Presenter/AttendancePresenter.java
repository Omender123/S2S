package com.edutech.Presenter;

import android.content.Context;

import com.edutech.Model.Request.SendOtpBody;
import com.edutech.Model.Response.TeacherResponse;
import com.edutech.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendancePresenter {

    private  AttendanceView view;

    public AttendancePresenter(AttendanceView view) {
        this.view = view;
    }

    public void AttendanceTeacher(Context context,String status) {
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.S2SApi(context).MarkTeacherAttendance(status);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onAttendanceSuccess(response.body(),response.message());
                } else {
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

    public  interface  AttendanceView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onAttendanceSuccess(ResponseBody response, String message);
        void onFailure(Throwable t);
    }
}
