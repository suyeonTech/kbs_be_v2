package com.bfriend.bfriend.room.dto.response;


import com.bfriend.bfriend.users.entity.Users;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class RoomDetailResponseDTO {
    private Users master;
    private List<Users> participants;
    private String roomName;
    private Integer maxPtc;
    private Integer joinPtc;
    private String location;
    private String restaurant;
    private String foodType;
    private LocalDateTime meetingTime;
    private Boolean isReported;

}
