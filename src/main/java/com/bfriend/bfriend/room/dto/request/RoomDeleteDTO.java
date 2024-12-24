package com.bfriend.bfriend.room.dto.request;

import com.bfriend.bfriend.users.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RoomDeleteDTO {

    private Users uid; //방장인지 확인

    private Integer rid; //선택된 모임방
}
