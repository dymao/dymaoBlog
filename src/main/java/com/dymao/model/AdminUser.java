package com.dymao.model;

/**
 * @author Mervin
 * @Description:
 * @date 2017-12-24 23:48
 */
public class AdminUser extends User {

    private String email;

    private String mobile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
}
