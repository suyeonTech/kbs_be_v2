package com.bfriend.bfriend.users.controller;

import com.bfriend.bfriend.utils.jwt.CustomUserDetails;
import com.bfriend.bfriend.users.dto.response.UserDetailResponse;
import com.bfriend.bfriend.users.service.UserService;
import com.bfriend.bfriend.users.dto.request.ChangePasswordRequest;
import com.bfriend.bfriend.users.dto.request.CheckAuthenticationNumberRequest;
import com.bfriend.bfriend.users.dto.request.CheckEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UsersController {
    private final UserService userService;

    @PostMapping("/changepw")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        return userService.changePassword(request);
    }

    @PostMapping("/checkemail")
    public CompletableFuture<ResponseEntity<String>> authenticationEmail(@RequestBody CheckEmailRequest request) {
        return userService.checkEmail(request);
    }

    @PostMapping("/findpw")
    public ResponseEntity<String> checkAuthenticationNumber(@RequestBody CheckAuthenticationNumberRequest request) {
        return userService.checkAuthenticationNumber(request);
    }

    @GetMapping("/detail")
    public ResponseEntity<UserDetailResponse> getProfileDetails(@AuthenticationPrincipal CustomUserDetails userDetails){
        return userService.getProfileDetail(userDetails);
    }

    // ✅프론트 백 연동 테스트
    @GetMapping("/test")
    public String hello() {
        return "테스트";
    }
}
