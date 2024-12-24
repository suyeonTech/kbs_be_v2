package com.bfriend.bfriend.room;

import com.bfriend.bfriend.room.dto.request.RoomCreateDTO;
import com.bfriend.bfriend.room.dto.request.RoomDeleteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;


    //모임방 생성
    public Room create(RoomCreateDTO roomCreateDTO) {
        //roomCreateDTO를 사용하여 room객체 생성
        Room room = Room.builder()
                .uid(roomCreateDTO.getUid())
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


}
