package com.bfriend.bfriend.users.dto.request;

public class CheckAuthenticationNumberRequest {
    private String email;
    private String authenticationNumber;

    public String getEmail() {
        return email;
    }

    public String getAuthenticationNumber() {
        return authenticationNumber;
    }
}
