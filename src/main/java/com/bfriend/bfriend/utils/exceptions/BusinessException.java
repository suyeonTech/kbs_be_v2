package com.bfriend.bfriend.utils.exceptions;

import lombok.Getter;

/**
 *여러 클래스에서 발생할 수 있는 비즈니스 예외를 처리하기 위한 공통 예외 클래스
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}