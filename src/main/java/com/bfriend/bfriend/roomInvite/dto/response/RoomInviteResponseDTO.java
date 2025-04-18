package com.bfriend.bfriend.roomInvite.dto.response;

import lombok.Getter;

@Getter
public class RoomInviteResponseDTO {
    private Long inviteId;
    private boolean accepted;   // 초대 수락 여부
}
