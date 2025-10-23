package com.bfriend.bfriend.blockedlist;

import com.bfriend.bfriend.users.Users;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class BlockedList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;

    @ManyToOne
    private Users addUid;

    @ManyToOne
    private Users addedUid;
}
