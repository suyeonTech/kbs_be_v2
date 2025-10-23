package com.bfriend.bfriend.roomptc.dto;

import com.bfriend.bfriend.room.Room;
import com.bfriend.bfriend.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
public class ParticipateRoomDTO {
    private final Room room;
    private final Users user;
}
