package com.bfriend.bfriend.roomInvite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomInviteListDTO {
    private Long inviteId;
    private String roomName;
    private String inviterNickname;
}
