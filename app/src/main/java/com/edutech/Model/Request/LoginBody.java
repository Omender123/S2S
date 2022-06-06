package com.edutech.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("password")
    @Expose
    public String password;

    /**
     * No args constructor for use in serialization
     */
    public LoginBody() {
    }

    /**
     * @param password
     * @param email
     */
    public LoginBody(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

}
