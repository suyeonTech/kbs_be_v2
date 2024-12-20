package com.bfriend.bfriend.users.service;

import com.bfriend.bfriend.users.dto.request.JoinDTO;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UsersRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String joinProcess(JoinDTO joinDTO) {

        if (isDuplicateUser(joinDTO.getNickname(), joinDTO.getEmail())) {
            return "이미 존재하는 유저입니다.";
        }

        Users users = buildUsers(joinDTO);
        userRepository.save(users);

        return "회원가입이 완료되었습니다.";
    }

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
