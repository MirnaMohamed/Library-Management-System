package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.request.UserLoginRequestDto;
import com.example.librarymanagementsystem.dto.request.UserRegisterRequestDto;
import com.example.librarymanagementsystem.dto.response.UserRegisterResponseDto;

public interface AuthService {
    void registerUser(UserRegisterRequestDto registerRequest);
    UserRegisterResponseDto registerAdmin(UserRegisterRequestDto adminDto);
    String loginUser(UserLoginRequestDto loginRequest);
}
