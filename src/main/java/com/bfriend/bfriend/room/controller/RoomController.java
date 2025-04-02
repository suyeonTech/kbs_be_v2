package com.bfriend.bfriend.room.controller;

import com.bfriend.bfriend.room.dto.response.MyRoomDetailResponseDTO;
import com.bfriend.bfriend.room.dto.response.VillageResponseDTO;
import com.bfriend.bfriend.room.service.RoomService;
import com.bfriend.bfriend.room.dto.request.RoomCreateDTO;
import com.bfriend.bfriend.room.dto.request.RoomDeleteDTO;
import com.bfriend.bfriend.room.dto.response.RoomDetailResponseDTO;
import com.bfriend.bfriend.room.entity.Room;
import com.bfriend.bfriend.security.CustomUserDetails;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.users.repository.UsersRepository;
import com.bfriend.bfriend.utils.exceptions.BusinessException;
import com.bfriend.bfriend.utils.exceptions.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = "/room")
@RestController
@Log4j2
public class RoomController {
    private final RoomService roomService;
    private final UsersRepository usersRepository;

    //모임방 생성
    @PostMapping("/create")
    public String createRoom(@RequestBody RoomCreateDTO roomCreateDTO) {
        Room room = roomService.create(roomCreateDTO);
        return "room_detail";
    }

    //모임방 삭제
    @PostMapping("/delete")
    public ResponseEntity<Object> deleteRoom(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody RoomDeleteDTO roomDeleteDTO)
    {
        String email = userDetails.getUsername();

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USERS_UIDNOTFOUND));

        return roomService.delete(user, roomDeleteDTO.getRid());
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

}
