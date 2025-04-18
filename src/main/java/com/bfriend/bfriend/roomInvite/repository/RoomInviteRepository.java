package com.bfriend.bfriend.roomInvite.repository;

import com.bfriend.bfriend.room.entity.Room;
import com.bfriend.bfriend.roomInvite.entity.RoomInvite;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.utils.enums.InviteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomInviteRepository extends JpaRepository<RoomInvite, Long> {
    boolean existsByRoomAndInviteeId(Room room, Users invitee);

    List<RoomInvite> findByInviteeIdAndInvStatus(Users invitee, InviteStatus status);

    Optional<RoomInvite> findByIdAndInviteeId(Long id, Users invitee);
}
