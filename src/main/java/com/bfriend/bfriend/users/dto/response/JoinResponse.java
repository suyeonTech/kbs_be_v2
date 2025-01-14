package com.bfriend.bfriend.users.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinResponse {
    private String message;
    private boolean success;
}
