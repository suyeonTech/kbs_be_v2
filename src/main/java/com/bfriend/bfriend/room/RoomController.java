package com.bfriend.bfriend.room;

import com.bfriend.bfriend.room.dto.request.RoomCreateDTO;
import com.bfriend.bfriend.room.dto.request.RoomDeleteDTO;
import com.bfriend.bfriend.room.dto.response.RoomDetailResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/room")
@RestController
public class RoomController {
    private final RoomService roomService;

    //모임방 생성
    @PostMapping("/create")
    public String createRoom(@RequestBody RoomCreateDTO roomCreateDTO) {
        Room room = roomService.create(roomCreateDTO);
        return "room_detail";
    }

    //모임방 삭제
    @PostMapping("/delete")
    public String deleteRoom(@RequestBody RoomDeleteDTO roomDeleteDTO) {
        int roomDeleted = roomService.delete(roomDeleteDTO); //성공 시 roomDeleted=1, 실패시 0
        if (roomDeleted == 0) { //실패시 오류 페이지 반환
            return "error page";
        }
        return "room_page"; //성공시 모임촌 페이지 반환
    }

  //상세보기
  @GetMapping("/detail/{roomId}")
  public RoomDetailResponseDTO getBoardDetail(@PathVariable Long roomId) {
    return roomService.getRoomDetail(roomId);
  }

}
