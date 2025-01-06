package com.bfriend.bfriend.users.controller;

import com.bfriend.bfriend.users.dto.request.RequestJoinDTO;
import com.bfriend.bfriend.users.service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/user/join")
    public ResponseEntity<?> joinUser(@Valid @ModelAttribute RequestJoinDTO requestJoinDTO, Errors errors, Model model) {

        // 유효성 검증 실패 시 처리
        if (errors.hasErrors()) {
            // 입력 데이터 유지
            model.addAttribute("requestJoinDTO", requestJoinDTO);

            // 에러 메시지 처리
            Map<String, String> validatorResult = joinService.validateHandling(errors);
            validatorResult.forEach(model::addAttribute);

            // 에러 정보를 반환
            return ResponseEntity.badRequest().body(validatorResult);
        }

        return joinService.joinProcess(requestJoinDTO);
    }
}
