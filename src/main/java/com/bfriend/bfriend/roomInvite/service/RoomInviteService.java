package com.bfriend.bfriend.roomInvite.service;

import com.bfriend.bfriend.room.entity.Room;
import com.bfriend.bfriend.room.repository.RoomRepository;
import com.bfriend.bfriend.roomInvite.dto.request.RoomInviteRequestDTO;
import com.bfriend.bfriend.roomInvite.entity.RoomInvite;
import com.bfriend.bfriend.roomInvite.repository.RoomInviteRepository;
import com.bfriend.bfriend.roomptc.entity.RoomPtc;
import com.bfriend.bfriend.roomptc.repository.RoomPtcRepository;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.users.repository.UsersRepository;
import com.bfriend.bfriend.utils.enums.InviteStatus;
import com.bfriend.bfriend.utils.exceptions.BusinessException;
import com.bfriend.bfriend.utils.exceptions.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomInviteService {

    private final RoomInviteRepository roomInviteRepository;
    private final UsersRepository usersRepository;
    private final RoomRepository roomRepository;
    private final RoomPtcRepository roomPtcRepository;

    public void sendInvite(RoomInviteRequestDTO dto){
        Users inviter = usersRepository.findByUid(dto.getInviterId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USERS_UIDNOTFOUND));
        Users friend = usersRepository.findByUid(dto.getFriendId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USERS_UIDNOTFOUND));
        Room room = roomRepository.findById(dto.getRid())
                .orElseThrow(() -> new BusinessException(ErrorCode.ROOM_NOTFOUND));

        if (roomInviteRepository.existsByRoomAndInviteeId(room, friend)) {
            throw new BusinessException(ErrorCode.USERS_DUPLICATED);
        }

        RoomInvite invite = RoomInvite.builder()
                .room(room)
                .inviterId(inviter)
                .inviteeId(friend)
                .invStatus(InviteStatus.PENDING)
                .build();

        roomInviteRepository.save(invite);
    }

    @Transactional
    public void acceptInvite(Long inviteId, Users currentUser){
        Users user = usersRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.USERS_UIDNOTFOUND));

        RoomInvite invite = roomInviteRepository.findByIdAndInviteeId(inviteId, user)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVITE_NOTFOUND_OR_NOT_AUTHORIZED));

        if (invite.getInvStatus() != InviteStatus.PENDING) {
            throw new BusinessException(ErrorCode.INVITE_ALREADY_HANDLED);
        }

        invite.accept();

        roomPtcRepository.save(RoomPtc.builder()
                .rid(invite.getRoom())
                .uid(user)
                .build()
        );
    }
}
