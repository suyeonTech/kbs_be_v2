package com.bfriend.bfriend.roomptc.dto;

import com.bfriend.bfriend.room.entity.Room;
import com.bfriend.bfriend.users.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
public class ParticipateRoomDTO {
    private Room room;
    private Users user;
}
