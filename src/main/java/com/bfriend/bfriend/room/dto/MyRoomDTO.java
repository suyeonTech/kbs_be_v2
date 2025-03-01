package com.bfriend.bfriend.room.dto;

import com.bfriend.bfriend.users.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MyRoomDTO {
    private Long rid;
    private String roomName;
    private String location;
    private String restaurant;
    private LocalDateTime meetingTime;
    private Integer maxPtc;
    private Integer joinPtc;
}