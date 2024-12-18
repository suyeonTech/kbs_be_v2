package com.bfriend.bfriend.users.dto.request;

import lombok.Getter;

@Getter
public class CheckAuthenticationNumberRequest {
    private String email;
    private String authenticationNumber;
}
