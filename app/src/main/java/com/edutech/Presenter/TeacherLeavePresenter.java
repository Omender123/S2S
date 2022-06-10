package com.edutech.Presenter;

import android.content.Context;

import com.edutech.Model.Request.TeacherLeaveBody;
import com.edutech.Model.Response.TeacherLeaveResponse;
import com.edutech.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherLeavePresenter {

    private TeacherLeaveView view;

    public TeacherLeavePresenter(TeacherLeaveView view) {
        this.view = view;
    }

    public void ApplyTeacherLeave(Context context, TeacherLeaveBody body) {
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.S2SApi(context).ApplyLeaveTeacher(body);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onTeacherLeaveSuccess(response.body(), response.message());
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

    public void GetAllTeacherLeave(Context context) {
        view.showHideProgress(true);
        Call<List<TeacherLeaveResponse>> userCall = AppUtils.S2SApi(context).GetAllTeacherLeaveHistory();
        userCall.enqueue(new Callback<List<TeacherLeaveResponse>>() {
            @Override
            public void onResponse(Call<List<TeacherLeaveResponse>> call, Response<List<TeacherLeaveResponse>> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onGetAllTeacherLeaveSuccess(response.body(), response.message());
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
            public void onFailure(Call<List<TeacherLeaveResponse>> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });


    }

    public interface TeacherLeaveView {
        void showHideProgress(boolean isShow);

        void onError(String message);

        void onTeacherLeaveSuccess(ResponseBody response, String message);

        void onGetAllTeacherLeaveSuccess(List<TeacherLeaveResponse> response, String message);

        void onFailure(Throwable t);
    }
}
