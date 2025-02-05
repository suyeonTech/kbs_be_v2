package com.bfriend.bfriend.users.dto.response;

import com.bfriend.bfriend.utils.enums.Gender;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Getter
@Builder
public class UserDetailResponse {
    private String email;
    private String nickname;
    private Gender gender;
    private int age;
    private boolean isReported;
    private boolean isStopped;
    private String profile;
}
