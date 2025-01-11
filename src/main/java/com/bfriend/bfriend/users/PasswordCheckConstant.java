package com.bfriend.bfriend.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PasswordCheckConstant {
    PASSWORD_MIN_LENGTH(8),
    PASSWORD_MAX_LENGTH(20),
    PASSWORD_REGEX("^(?=.*[a-zA-Z])(?=.*[~!@#$%^&*+=()_-])(?=.*[0-9]).+$");

    private final Object value;
}
