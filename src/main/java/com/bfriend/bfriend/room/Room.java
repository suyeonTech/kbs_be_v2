package com.bfriend.bfriend.room;

import com.bfriend.bfriend.room.dto.request.RoomCreateDTO;
import com.bfriend.bfriend.users.entity.Users;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @ManyToOne
    private Users masterUid;

    private LocalDateTime meetingTime;

    @Column(length = 20)
    private String roomName;

    private String location;

    private String restaurant;

    private String foodType;

    private Boolean isReported;

    private Integer maxPtc;

    private Integer joinPtc;



}
