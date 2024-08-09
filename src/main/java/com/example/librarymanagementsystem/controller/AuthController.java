package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.request.UserLoginRequestDto;
import com.example.librarymanagementsystem.dto.request.UserRegisterRequestDto;
import com.example.librarymanagementsystem.dto.response.UserRegisterResponseDto;
import com.example.librarymanagementsystem.factory.impl.SuccessResponseFactory200;
import com.example.librarymanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final SuccessResponseFactory200 responseFactory;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequestDto registerRequestDto) {
        authService.registerUser(registerRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(null,"User registered successfully "));
    }

    @PostMapping(value= "/admin/register")
    public ResponseEntity<?> registerAdmin(@RequestBody UserRegisterRequestDto registerRequest) {

        UserRegisterResponseDto admin = authService.registerAdmin(registerRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(admin,"Admin registered successfully "));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequestDto loginRequest) {
        String authToken = authService.loginUser(loginRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(authToken, "Login successful"));
    }
}
