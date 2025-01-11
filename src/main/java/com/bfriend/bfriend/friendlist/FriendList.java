package com.bfriend.bfriend.friendlist;

import com.bfriend.bfriend.users.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
public class FriendList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;

    @ManyToOne
    private Users addUid;

    @ManyToOne
    private Users addedUid;

    protected FriendList() {}
}
