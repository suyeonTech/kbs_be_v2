package com.bfriend.bfriend.roomInvite.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomInviteResponseDTO {
    private Long inviteId;
    private String roomName;
    private String inviterNickname;    // 초대 요청을 보낸 유저의 닉네임
    private boolean accepted;
}
