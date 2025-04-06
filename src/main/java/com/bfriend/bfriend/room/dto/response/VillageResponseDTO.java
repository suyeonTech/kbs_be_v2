package com.bfriend.bfriend.room.dto.response;

import com.bfriend.bfriend.room.dto.MyRoomDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VillageResponseDTO {
    List<MyRoomDTO> village;
    Long uid;
}
