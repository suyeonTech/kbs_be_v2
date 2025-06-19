package com.bfriend.bfriend.roomInvite.repository;

import com.bfriend.bfriend.room.entity.Room;
import com.bfriend.bfriend.roomInvite.entity.RoomInvite;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.utils.enums.InviteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomInviteRepository extends JpaRepository<RoomInvite, Long> {
    boolean existsByRoomAndInviteeIdAndInvStatus(Room room, Users inviteeId, InviteStatus status);

    List<RoomInvite> findByInviteeIdUidAndInvStatus(Long inviteeId, InviteStatus status);

    Optional<RoomInvite> findByIdAndInviteeId(Long id, Users invitee);
}
