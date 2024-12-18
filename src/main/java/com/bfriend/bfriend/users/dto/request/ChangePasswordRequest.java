package com.bfriend.bfriend.users.dto.request;

import lombok.Getter;

@Getter
public class ChangePasswordRequest {
    private String email;
    private String newPassword;
}
