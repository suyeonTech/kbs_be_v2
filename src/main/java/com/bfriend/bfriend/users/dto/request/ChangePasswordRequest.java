package com.bfriend.bfriend.users.dto.request;

public class ChangePasswordRequest {
    private String email;
    private String newPassword;

    public String getEmail() {
        return email;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
