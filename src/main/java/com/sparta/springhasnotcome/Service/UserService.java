package com.sparta.springhasnotcome.Service;

import com.sparta.springhasnotcome.Dto.SignupRequestDto;
import com.sparta.springhasnotcome.Models.User;
import com.sparta.springhasnotcome.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
//        Optional<User> found = userRepository.findByUsername(username);
//        if (found.isPresent()) {
//            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
//        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String passwordCheck = passwordEncoder.encode(requestDto.getPasswordCheck());

        String email = requestDto.getEmail();

//        String passwordCheck = requestDto.getPasswordCheck();

//        if (!password.equals(requestDto.getUsername())){
//            throw new IllegalArgumentException("비밀번호의 닉네임과 같은 값을 넣을 수 없습니다.");
//        }else if (!password.equals(passwordCheck)) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//
//
//        String email = requestDto.getEmail();
//        Optional<User> found2 = userRepository.findByEmail(email);
//        if (found2.isPresent()){
//            throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
//        }

        User user = new User(username, password, email, passwordCheck);
        userRepository.save(user);
    }



}