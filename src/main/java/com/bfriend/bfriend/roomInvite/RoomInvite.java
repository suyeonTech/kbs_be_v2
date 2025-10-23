package com.bfriend.bfriend.roomInvite;

import com.bfriend.bfriend.room.Room;
import com.bfriend.bfriend.utils.enums.InviteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.bfriend.bfriend.users.Users;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Users inviterId;

    @ManyToOne
    private Users inviteeId;

    //요청 상태
    @Enumerated(EnumType.STRING)
    private InviteStatus invStatus;

    public void accept() {
        invStatus = InviteStatus.ACCEPTED;
    }

    public void decline() {
        this.invStatus = InviteStatus.DECLINED;
    }

}
