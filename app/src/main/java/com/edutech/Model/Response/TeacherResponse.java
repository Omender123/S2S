package com.edutech.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeacherResponse {

    @SerializedName("res")
    @Expose
    private Res res;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("message")
    @Expose
    private String message;

    public Res getRes() {
        return res;
    }

    public void setRes(Res res) {
        this.res = res;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Res {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("DOB")
        @Expose
        private String dob;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("otp2")
        @Expose
        private String otp2;
        @SerializedName("isEmailVerified")
        @Expose
        private Boolean isEmailVerified;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("department")
        @Expose
        private List<Object> department = null;
        @SerializedName("designation")
        @Expose
        private String designation;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("active")
        @Expose
        private Boolean active;
        @SerializedName("profile")
        @Expose
        private String profile;
        @SerializedName("roleId")
        @Expose
        private Integer roleId;
        @SerializedName("blockedByAdmin")
        @Expose
        private Boolean blockedByAdmin;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("school")
        @Expose
        private String school;
        @SerializedName("qualification")
        @Expose
        private String qualification;
        @SerializedName("certificate")
        @Expose
        private String certificate;
        @SerializedName("document")
        @Expose
        private Document document;
        @SerializedName("experience")
        @Expose
        private String experience;
        @SerializedName("employmentType")
        @Expose
        private String employmentType;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;
        @SerializedName("__v")
        @Expose
        private Integer v;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getOtp2() {
            return otp2;
        }

        public void setOtp2(String otp2) {
            this.otp2 = otp2;
        }

        public Boolean getIsEmailVerified() {
            return isEmailVerified;
        }

        public void setIsEmailVerified(Boolean isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<Object> getDepartment() {
            return department;
        }

        public void setDepartment(List<Object> department) {
            this.department = department;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        public Boolean getBlockedByAdmin() {
            return blockedByAdmin;
        }

        public void setBlockedByAdmin(Boolean blockedByAdmin) {
            this.blockedByAdmin = blockedByAdmin;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getQualification() {
            return qualification;
        }

        public void setQualification(String qualification) {
            this.qualification = qualification;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public Document getDocument() {
            return document;
        }

        public void setDocument(Document document) {
            this.document = document;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getEmploymentType() {
            return employmentType;
        }

        public void setEmploymentType(String employmentType) {
            this.employmentType = employmentType;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }
        public class Document {

            @SerializedName("docNumber")
            @Expose
            private String docNumber;
            @SerializedName("type")
            @Expose
            private String type;
            @SerializedName("link")
            @Expose
            private String link;

            public String getDocNumber() {
                return docNumber;
            }

            public void setDocNumber(String docNumber) {
                this.docNumber = docNumber;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

        }
    }
}