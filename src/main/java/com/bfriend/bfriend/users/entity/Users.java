package com.bfriend.bfriend.users.entity;

import com.bfriend.bfriend.utils.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

    private String email;

    @ColumnDefault("false")
    private Boolean isReported;

    @ColumnDefault("false")
    private Boolean isStopped;

    private String profile;

    @Column(length = 20, nullable = false)
    private String role;

}

