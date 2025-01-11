package com.bfriend.bfriend.utils.constants;

public final class ValidationConstants {

    // 유효성 검사
    public static final String VALIDATION_MESSAGE = " 필수 입력 값입니다.";
    public static final String NICKNAME_REGEXP = "^[가-힣a-zA-Z0-9]{2,10}$";
    public static final String PASSWORD_REGEXP = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*+=()_-]).{8,15}$";

}
