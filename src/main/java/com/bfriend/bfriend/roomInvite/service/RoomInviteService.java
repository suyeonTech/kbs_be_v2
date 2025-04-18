package com.bfriend.bfriend.roomInvite.service;

import com.bfriend.bfriend.room.entity.Room;
import com.bfriend.bfriend.room.repository.RoomRepository;
import com.bfriend.bfriend.roomInvite.dto.request.RoomInviteRequestDTO;
import com.bfriend.bfriend.roomInvite.entity.RoomInvite;
import com.bfriend.bfriend.roomInvite.repository.RoomInviteRepository;
import com.bfriend.bfriend.roomptc.repository.RoomPtcRopository;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.users.repository.UsersRepository;
import com.bfriend.bfriend.utils.enums.InviteStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomInviteService {

    private final RoomInviteRepository roomInviteRepository;
    private final UsersRepository usersRepository;
    private final RoomRepository roomRepository;
    private final RoomPtcRopository roomPtcRopository;

    public void sendInvite(RoomInviteRequestDTO dto){
        Users inviter = usersRepository.findByUid(dto.getInviterId())
                .orElseThrow(() -> new IllegalArgumentException("초대한 유저가 존재하지 않습니다."));
        Users friend = usersRepository.findByUid(dto.getFriendId())
                .orElseThrow(() -> new IllegalArgumentException("초대받을 친구가 존재하지 않습니다."));
        Room room = roomRepository.findById(dto.getRid())
                .orElseThrow(() -> new IllegalArgumentException("해당 방이 존재하지 않습니다."));

        if (roomInviteRepository.existsByRoomAndInviteeId(room, friend)) {
            throw new IllegalStateException("이미 초대 된 친구입니다.");
        }

        RoomInvite invite = RoomInvite.builder()
                .room(room)
                .inviterId(inviter)
                .inviteeId(friend)
                .invStatus(InviteStatus.PENDING)
                .build();

        roomInviteRepository.save(invite);
    }
}
