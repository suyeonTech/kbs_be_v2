package com.bfriend.bfriend.room.dto.response;

import com.bfriend.bfriend.room.dto.MyRoomDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MyRoomDetailResponseDTO {
    //내가 만든 방
    private List<MyRoomDTO> createdRooms;
    //내가 참여한 방
    private List<MyRoomDTO> joinedRooms;
}
