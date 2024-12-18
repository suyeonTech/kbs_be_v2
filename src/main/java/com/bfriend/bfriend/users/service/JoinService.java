package com.bfriend.bfriend.users.service;

import com.bfriend.bfriend.users.dto.JoinDTO;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.users.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UsersRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String joinProcess(JoinDTO joinDTO) {

        if (isDuplicateUser(joinDTO.getNickname(), joinDTO.getEmail())) {
            return "이미 존재하는 유저입니다.";
        }

        // 새로운 유저 엔티티 생성
        Users users = buildUsers(joinDTO);
        userRepository.save(users);

        return "회원가입이 완료되었습니다.";
    }

    // 중복 검사
    private boolean isDuplicateUser(String nickname, String email) {
        return userRepository.findByNickname(nickname).isPresent() || userRepository.findByEmail(email).isPresent();
    }

    private Users buildUsers(JoinDTO joinDTO) {
        return Users.builder()
                .nickname(joinDTO.getNickname())
                .password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
                .gender(joinDTO.getGender())
                .age(joinDTO.getAge())
                .email(joinDTO.getEmail())
                .isReported(false)
                .isStopped(false)
                .build();
    }
}
