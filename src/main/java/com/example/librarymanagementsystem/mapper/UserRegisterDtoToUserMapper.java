package com.example.librarymanagementsystem.mapper;

import com.example.librarymanagementsystem.dto.request.UserRegisterRequestDto;
import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserRegisterDtoToUserMapper implements Function<UserRegisterRequestDto, User> {
    private final PasswordEncoder passwordEncoder;
    @Override
    public User apply(UserRegisterRequestDto userRegisterDto) {
        return apply(userRegisterDto,Role.PATRON);
    }

    public User apply(UserRegisterRequestDto adminRequestDto, Role role) {
        return User.builder()
                .email(adminRequestDto.email())
                .firstName(adminRequestDto.firstName())
                .lastName(adminRequestDto.lastName())
                .role(role)
                .gender(adminRequestDto.gender())
                .password(passwordEncoder.encode(adminRequestDto.password()))
                .build();
    }
}
