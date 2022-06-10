package com.edutech.Api;


import com.edutech.Model.Request.LoginBody;
import com.edutech.Model.Request.SendOtpBody;
import com.edutech.Model.Request.TeacherLeaveBody;
import com.edutech.Model.Response.TeacherLeaveResponse;
import com.edutech.Model.Response.TeacherResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("teacher/login")
    Call<TeacherResponse> TeacherLogin(@Body LoginBody body);

    @POST("teacher/sendOtp")
    Call<ResponseBody> SendOtp(@Body SendOtpBody body);

    @POST("teacher/emailVerify")
    Call<ResponseBody> VerifyOTP(@Body SendOtpBody body);

    @PUT("teacher/updatePassword")
    Call<ResponseBody> ChangePassword(@Body LoginBody body);

    @FormUrlEncoded
    @POST("teacher/markAttendance")
    Call<ResponseBody>MarkTeacherAttendance(@Field("status") String status);

    @POST("teacher/applyForLeave")
    Call<ResponseBody> ApplyLeaveTeacher(@Body TeacherLeaveBody body);

    @GET("teacher/LeaveHistory")
    Call<List<TeacherLeaveResponse>> GetAllTeacherLeaveHistory();
}

