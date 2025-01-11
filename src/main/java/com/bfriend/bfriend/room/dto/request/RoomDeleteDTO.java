package com.bfriend.bfriend.room.dto.request;

import com.bfriend.bfriend.users.entity.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RoomDeleteDTO {

    private Users uid; //방장인지 확인

    private Long rid; //선택된 모임방
}
