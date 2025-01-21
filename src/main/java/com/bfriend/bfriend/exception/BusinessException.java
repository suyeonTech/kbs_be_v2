package com.bfriend.bfriend.exception;

/**
 *여러 클래스에서 발생할 수 있는 비즈니스 예외를 처리하기 위한 공통 예외 클래스
 */
public class BusinessException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public BusinessException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
