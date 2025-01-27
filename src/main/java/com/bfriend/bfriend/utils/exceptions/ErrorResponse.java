package com.bfriend.bfriend.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int status;
    private String code;
    private String message;
    private Map<String, String> errors; // 검증 실패 항목

}
