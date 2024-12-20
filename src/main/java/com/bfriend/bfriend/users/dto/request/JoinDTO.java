package com.bfriend.bfriend.users.dto.request;

import com.bfriend.bfriend.utils.enums.Gender;
import lombok.Builder;
import lombok.Getter;
//import lombok.Setter;

//@Setter
@Getter
@Builder
public class JoinDTO {

    private String nickname;
    private String password;
    private Gender gender;
    private Integer age;
    private String email;

}
