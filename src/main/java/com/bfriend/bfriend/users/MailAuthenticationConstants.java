package com.bfriend.bfriend.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MailAuthenticationConstants {
    EMAIL_AUTHENTICATION_NUMBER_NAMESPACE("email_authentication:"),
    MIN_AUTHENTICATION_NUMBER(100000),
    RANGE_RANDOM_AUTHENTICATION_NUMBER(900000);

    private final Object value;
}
