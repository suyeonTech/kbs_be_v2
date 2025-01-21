package com.bfriend.bfriend.room;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomRequestDTO {
    private Long rid;
    private Long uid;
}
