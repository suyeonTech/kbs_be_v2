package com.bfriend.bfriend.users.controller;

import com.bfriend.bfriend.users.dto.request.RequestJoinDTO;
import com.bfriend.bfriend.users.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<?> joinUser(@ModelAttribute RequestJoinDTO requestJoinDTO) {
        return joinService.joinProcess(requestJoinDTO);
    }
}
