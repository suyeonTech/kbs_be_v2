package com.bfriend.bfriend.friendlist.dto.request;

import lombok.Getter;

@Getter
public class UserIdAndFriendIdRequest {
    private Long userId;
    private Long friendId;
}
