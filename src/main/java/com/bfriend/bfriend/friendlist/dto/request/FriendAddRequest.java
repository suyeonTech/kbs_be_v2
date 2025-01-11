package com.bfriend.bfriend.friendlist.dto.request;

import lombok.Getter;

@Getter
public class FriendAddRequest {
    private Long userId;
    private Long friendId;
}
