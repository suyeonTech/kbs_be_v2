package com.bfriend.bfriend.roomInvite;

import com.bfriend.bfriend.roomInvite.dto.request.RoomInviteRequestDTO;
import com.bfriend.bfriend.roomInvite.dto.response.RoomInviteResponseDTO;
import com.bfriend.bfriend.utils.jwt.CustomUserDetails;
import com.bfriend.bfriend.utils.exceptions.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<SuccessResponse> accept(@PathVariable Long inviteId, @AuthenticationPrincipal CustomUserDetails user){
        roomInviteService.acceptInvite(inviteId, user.getUserEntity());

        return ResponseEntity.ok(new SuccessResponse(200, "초대 수락 완료"));
    }

    // 초대 목록 조회
    @GetMapping("/list")
    public ResponseEntity<SuccessResponse> getMyInvites(@AuthenticationPrincipal CustomUserDetails user) {
        List<RoomInviteResponseDTO> result = roomInviteService.getMyInvites(user);

        return ResponseEntity.ok(new SuccessResponse(200, "초대 목록 조회 성공", result));
    }

    // 초대 거절
    @PostMapping("/decline/{inviteId}")
    public ResponseEntity<SuccessResponse> declineInvite(@PathVariable Long inviteId, @AuthenticationPrincipal CustomUserDetails user){
        roomInviteService.declineInvite(inviteId, user);

        return ResponseEntity.ok(new SuccessResponse(200, "초대 거절 완료"));
    }
}