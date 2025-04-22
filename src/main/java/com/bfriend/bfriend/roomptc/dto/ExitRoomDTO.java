package com.bfriend.bfriend.roomptc.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExitRoomDTO {
    private String roomId;
    private String userId;
}
