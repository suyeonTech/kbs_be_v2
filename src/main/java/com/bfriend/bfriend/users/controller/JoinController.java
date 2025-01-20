package com.bfriend.bfriend.users.controller;

import com.bfriend.bfriend.users.dto.request.JoinRequest;
import com.bfriend.bfriend.users.service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<?> joinUser(@Valid @RequestBody JoinRequest joinRequest) {
        return joinService.joinProcess(joinRequest);
    }
}
