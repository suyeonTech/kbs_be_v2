package com.bfriend.bfriend.users.dto.request;

import com.bfriend.bfriend.utils.enums.Gender;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestJoinDTO {

    private String nickname;
    private String password;
    private Gender gender;
    private Integer age;
    private String email;

}
