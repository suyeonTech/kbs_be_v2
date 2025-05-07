package com.bfriend.bfriend.roomInvite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomInviteResponseDTO {
    private Long inviterId;      // 초대자 아이디
    private boolean accepted;   // 초대 수락 여부
}
