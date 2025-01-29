package com.bfriend.bfriend.security;


import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users userData = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("일치하는 이메일이 존재하지 않습니다: " + email));

        return new CustomUserDetails(userData);
    }
}