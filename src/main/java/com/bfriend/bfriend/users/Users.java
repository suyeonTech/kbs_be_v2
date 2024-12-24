package com.bfriend.bfriend.users;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String nickname;

    private Boolean gender;

    private Integer age;

    @ColumnDefault("false")
    private Boolean isReported;

    @ColumnDefault("false")
    private Boolean isStopped;

    private String profile;
}
