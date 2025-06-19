package com.bfriend.bfriend.friendlist.dto.response;

import com.bfriend.bfriend.utils.enums.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FriendProfileResponse {
    private final String profile;
    private final String nickname;
    private final String email;
    private final Integer age;
    private final Gender gender;
}
