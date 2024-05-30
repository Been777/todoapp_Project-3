package com.sparta.todoapp_project3.service;

import com.sparta.todoapp_project3.dto.SignupRequestDto;
import com.sparta.todoapp_project3.entity.User;
import com.sparta.todoapp_project3.entity.UserRoleEnum;
import com.sparta.todoapp_project3.jwt.JwtUtil;
import com.sparta.todoapp_project3.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        if (username == null || !Pattern.matches("^[a-z0-9]{4,10}$", username)) {
            throw new IllegalArgumentException("아이디는 4자 이상 10자 이하의 알파벳 소문자와 숫자로 구성되어야 합니다.");
        }

        if (password == null || !Pattern.matches("^[a-zA-Z0-9]{8,15}$", password)) {
            throw new IllegalArgumentException("비밀번호는 8자 이상 15자 이하의 알파벳 대소문자와 숫자로 구성되어야 합니다.");
        }

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
            // JWT 예외 처리 하기
            // - DB에 이미 존재하는 `username`으로 회원가입을 요청할 때
            //    - 에러 메세지 : 중복된 `username` 입니다.
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(username, password, email, role);
        userRepository.save(user);
    }

    public String registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();
        UserRoleEnum role = UserRoleEnum.USER;
        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        // 사용자 등록
        User user = new User(username, password, email, role);
        userRepository.save(user);
        return "회원가입에 성공했습니다.";
    }
}