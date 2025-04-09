package com.bfriend.bfriend.users.controller;

import com.bfriend.bfriend.users.dto.request.JoinRequest;
import com.bfriend.bfriend.users.service.AuthService;
import com.bfriend.bfriend.users.service.JoinService;
import com.bfriend.bfriend.utils.exceptions.BusinessException;
import com.bfriend.bfriend.utils.exceptions.ErrorCode;
import com.bfriend.bfriend.utils.exceptions.SuccessResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JoinService joinService;
    private final AuthService authService ;

    @PostMapping("/join")
    public ResponseEntity<?> joinUser(@Valid @RequestBody JoinRequest joinRequest) {
        return joinService.joinProcess(joinRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<SuccessResponse> logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new BusinessException(ErrorCode.AUTHENTICATION_HEADER_MISSING);
        }

        authService.logout(header);

        SuccessResponse successResponse = new SuccessResponse(
                HttpServletResponse.SC_OK,
                "로그아웃에 성공하셨습니다."
        );

        return ResponseEntity.ok(successResponse);
    }
}
