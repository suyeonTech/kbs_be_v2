package com.bfriend.bfriend.friendlist.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FriendsListResponse {
    private final Long uid;
    private final String nickname;
    private final String profile;
}
