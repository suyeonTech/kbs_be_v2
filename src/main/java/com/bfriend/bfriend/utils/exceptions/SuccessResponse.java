package com.bfriend.bfriend.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {
    private int status;
    private String message;
    private Map<String, Object> data;

    // 반환 데이터가 없는 경우
    public SuccessResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
