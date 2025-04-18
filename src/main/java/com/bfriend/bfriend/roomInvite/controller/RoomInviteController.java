package com.bfriend.bfriend.roomInvite.controller;

import com.bfriend.bfriend.roomInvite.dto.request.RoomInviteRequestDTO;
import com.bfriend.bfriend.roomInvite.service.RoomInviteService;
import com.bfriend.bfriend.utils.exceptions.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invite")
@RequiredArgsConstructor
public class RoomInviteController {

    private final RoomInviteService roomInviteService;

    // 친구 초대
    @PostMapping("/send")
    public ResponseEntity<SuccessResponse> send(@RequestBody RoomInviteRequestDTO dto){
        roomInviteService.sendInvite(dto);

        SuccessResponse response = new SuccessResponse(200, "초대 전송 완료");
        return ResponseEntity.ok(response);
    }
}
