package com.example.librarymanagementsystem.mapper;

import com.example.librarymanagementsystem.dto.response.UserRegisterResponseDto;
import com.example.librarymanagementsystem.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToUserResponseDtoMapper implements Function<User, UserRegisterResponseDto> {
    @Override
    public UserRegisterResponseDto apply(User user) {
        return UserRegisterResponseDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
