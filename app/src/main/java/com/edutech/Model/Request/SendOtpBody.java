package com.edutech.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendOtpBody {
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("otp")
    @Expose
    public String otp;

    /**
     * No args constructor for use in serialization
     */
    public SendOtpBody() {
    }

    /**
     * @param otp
     * @param email
     */
    public SendOtpBody(String email, String otp) {
        super();
        this.email = email;
        this.otp = otp;
    }
}
