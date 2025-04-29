package com.bfriend.bfriend.roomInvite.controller;

import com.bfriend.bfriend.roomInvite.dto.request.RoomInviteRequestDTO;
import com.bfriend.bfriend.roomInvite.service.RoomInviteService;
import com.bfriend.bfriend.security.CustomUserDetails;
import com.bfriend.bfriend.utils.exceptions.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    // 초대 수락
    @PostMapping("/accept/{inviteId}")
    public ResponseEntity<SuccessResponse> accept(@PathVariable Long inviteId, @AuthenticationPrincipal CustomUserDetails userDetails){
        roomInviteService.acceptInvite(inviteId, userDetails.getUserEntity());

        return ResponseEntity.ok(new SuccessResponse(200, "초대 수락 완료0"));
    }
}
