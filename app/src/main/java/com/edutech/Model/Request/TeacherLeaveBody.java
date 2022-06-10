package com.edutech.Model.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeacherLeaveBody {
    @SerializedName("reason")
    @Expose
    public String reason;
    @SerializedName("days")
    @Expose
    public List<String> days = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public TeacherLeaveBody() {
    }

    /**
     *
     * @param reason
     * @param days
     */
    public TeacherLeaveBody(String reason, List<String> days) {
        super();
        this.reason = reason;
        this.days = days;
    }

}

