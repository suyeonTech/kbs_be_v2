package com.bfriend.bfriend.roomptc;

import com.bfriend.bfriend.exception.ExceptionCode;
import com.bfriend.bfriend.exception.NotFoundException;
import com.bfriend.bfriend.room.Room;
import com.bfriend.bfriend.room.RoomRepository;

import com.bfriend.bfriend.users.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoomPtcService {

    private final RoomPtcRopository roomPtcRopository;
    private final RoomRepository roomRepository;

    public List<Users> getParticipants(Long roomId){
        Room foundRoom  = roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.ROOM_NOTFOUND,roomId.toString()));
        List<Users> participants = roomPtcRopository.findUsersByRoom(foundRoom);

        //참여자가 없을 경우 로그로 알림
        if (participants.isEmpty()) {
            log.info("방 ID {}에 참여자가 없습니다.", foundRoom.getRid());
        }
        // null 대신 빈 리스트 반환
        return participants;
    }
}
