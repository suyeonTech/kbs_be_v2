package com.bfriend.bfriend.room.controller;

import com.bfriend.bfriend.room.dto.response.MyRoomDetailResponseDTO;
import com.bfriend.bfriend.room.dto.response.VillageResponseDTO;
import com.bfriend.bfriend.room.service.RoomService;
import com.bfriend.bfriend.room.dto.request.RoomCreateDTO;
import com.bfriend.bfriend.room.dto.request.RoomDeleteDTO;
import com.bfriend.bfriend.room.dto.response.RoomDetailResponseDTO;
import com.bfriend.bfriend.room.entity.Room;
import com.bfriend.bfriend.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = "/room")
@RestController
@Log4j2
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

    //내 방 상세보기
    @GetMapping("/myroom/{userId}")
    public ResponseEntity<MyRoomDetailResponseDTO> getMyRoom(@PathVariable Long userId) {
        return roomService.getMyRoomDetail(userId);
    }

    //모임촌 보기
    @GetMapping("/village")
    public ResponseEntity<VillageResponseDTO> getVillage() {
        return roomService.getVillage();
    }

    //모임방 참여하기
    @PostMapping("/join/{roomId}")
    public ResponseEntity joinRoom(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long roomId) {
        return roomService.joinRoom(userDetails, roomId);
    }
}
