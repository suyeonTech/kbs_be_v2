package com.bfriend.bfriend.room.service;


import com.bfriend.bfriend.room.dto.MyRoomDTO;
import com.bfriend.bfriend.room.dto.response.MyRoomDetailResponseDTO;
import com.bfriend.bfriend.room.dto.response.VillageResponseDTO;
import com.bfriend.bfriend.room.entity.Room;
import com.bfriend.bfriend.room.repository.RoomRepository;
import com.bfriend.bfriend.roomptc.repository.RoomPtcRopository;
import com.bfriend.bfriend.security.CustomUserDetails;
import com.bfriend.bfriend.users.repository.UsersRepository;
import com.bfriend.bfriend.users.service.UserService;
import com.bfriend.bfriend.utils.exceptions.BusinessException;
import com.bfriend.bfriend.utils.exceptions.ErrorCode;
import com.bfriend.bfriend.utils.exceptions.NotFoundException;
import com.bfriend.bfriend.room.dto.request.RoomCreateDTO;
import com.bfriend.bfriend.room.dto.request.RoomDeleteDTO;
import com.bfriend.bfriend.room.dto.response.RoomDetailResponseDTO;
import com.bfriend.bfriend.roomptc.service.RoomPtcService;
import com.bfriend.bfriend.users.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bfriend.bfriend.room.mapper.RoomMapper.*;

@RequiredArgsConstructor
@Service
@Log4j2
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomPtcService roomPtcService;
    private final UsersRepository usersRepository;
    private final RoomPtcRopository roomPtcRopository;

    //모임방 생성
    public Room create(CustomUserDetails userDetails, RoomCreateDTO roomCreateDTO) {

        String email = userDetails.getUsername();

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USERS_UIDNOTFOUND));

        //roomCreateDTO를 사용하여 room객체 생성
        Room room = Room.builder()
                .masterUid(user)
                .meetingTime(roomCreateDTO.getMeetingTime())
                .roomName(roomCreateDTO.getRoomName())
                .location(roomCreateDTO.getLocation())
                .restaurant(roomCreateDTO.getRestaurant())
                .foodType(roomCreateDTO.getFoodType())
                .maxPtc(roomCreateDTO.getMaxPtc())
                .isReported(false) //초기값 false(신고되지 않음)
                .joinPtc(1) //초기값 1(방장 1명)
                .build();

        roomRepository.save(room); //DB에 저장
        return room; //room객체 반환
    }

    //모임방 삭제. 성공시 1, 실패시 0 반환
    public int delete(RoomDeleteDTO roomDeleteDTO) {
        roomRepository.delete(roomRepository.findByRid(roomDeleteDTO.getRid()));
        if (roomRepository.findByRid(roomDeleteDTO.getRid()) != null) { //삭제 시도한 데이터가 아직 남아있으면 오류처리
            return 0;
        }
        return 1; //성공시
    }

    //검색 키워드로 검색결과를 찾아 리스트로 반환
    public List<Room> searchRoom(String keyword) {
        return roomRepository.findAllByKeyword(keyword);
    }

    //모임방 상세보기
    public RoomDetailResponseDTO getRoomDetail(Long roomId) {
        Room foundRoom  = roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ROOM_NOTFOUND,roomId.toString()));

        List<Users> participants = roomPtcService.getParticipants(roomId);
        log.debug("참여자 : {}",participants.size());

        return RoomDetailResponseDTO.builder()
                .master(toUserDTO(foundRoom.getMasterUid()))
                .participants(toUserDTOs(participants))
                .roomName(foundRoom.getRoomName())
                .location(foundRoom.getLocation())
                .restaurant(foundRoom.getRestaurant())
                .foodType(foundRoom.getFoodType())
                .meetingTime(foundRoom.getMeetingTime())
                .isReported(foundRoom.getIsReported())
                .build();
    }

    //나와 관련된 방 상세보기
    public ResponseEntity<MyRoomDetailResponseDTO> getMyRoomDetail(Long userId){
        Users master = usersRepository.findByUid(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USERS_UIDNOTFOUND));

        List<Room> createdRooms = roomRepository.findAllByMasterUid(master.getUid());
        log.debug("내가 만든 모임방 : {}",createdRooms.size());
        List<MyRoomDTO> createdDTOs = toRoomDTOs(createdRooms);

        List<Room> joinedRooms = roomPtcRopository.findAllByUid(master.getUid());
        log.debug("내가 참여한 모임방 : {}",joinedRooms.size());
        List<MyRoomDTO> joinedDTOs = toRoomDTOs(joinedRooms);

        return ResponseEntity.ok(
                MyRoomDetailResponseDTO.builder()
                .createdRooms(createdDTOs)
                .joinedRooms(joinedDTOs)
                .build());
    }

    //모든 모임방(모임촌) 보기
    public ResponseEntity<VillageResponseDTO> getVillage() {
        List<Room> allRooms = roomRepository.findAll();
        log.debug("모임촌에 존재하는 총 모임방 : {}",allRooms.size());

        List<MyRoomDTO> allRoomDTOs = toRoomDTOs(allRooms);

        return ResponseEntity.ok(
                VillageResponseDTO.builder()
                        .village(allRoomDTOs)
                        .build());

    }

}
