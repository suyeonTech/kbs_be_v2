package com.bfriend.bfriend.users.controller;

import com.bfriend.bfriend.users.dto.JoinDTO;
import com.bfriend.bfriend.users.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO){

        joinService.joinProcess(joinDTO);
        return "ok";
    }
}
