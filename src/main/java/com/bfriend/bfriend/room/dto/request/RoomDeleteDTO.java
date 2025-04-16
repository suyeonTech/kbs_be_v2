package com.bfriend.bfriend.room.dto.request;

import com.bfriend.bfriend.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RoomDeleteDTO {
    private Long rid; //선택된 모임방

}
