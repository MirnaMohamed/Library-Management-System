package com.example.librarymanagementsystem.service.Impl;

import com.example.librarymanagementsystem.dto.request.UserLoginRequestDto;
import com.example.librarymanagementsystem.dto.request.UserRegisterRequestDto;
import com.example.librarymanagementsystem.dto.response.UserRegisterResponseDto;
import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.enums.Role;
import com.example.librarymanagementsystem.helper.UserHelper;
import com.example.librarymanagementsystem.mapper.*;
import com.example.librarymanagementsystem.repository.UserRepository;
import com.example.librarymanagementsystem.security.JwtService;
import com.example.librarymanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserHelper userHelper;
    private final PasswordEncoder passwordEncoder;
    private final UserRegisterDtoToUserMapper userRegisterDtoToUserMapper;
    private final UserToUserResponseDtoMapper userResponseDtoMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void registerUser(UserRegisterRequestDto registerRequest) {
        userHelper.checkUserExistAndPasswordEqualAfterRegister(registerRequest);
        User user = userRegisterDtoToUserMapper.apply(registerRequest);
        userRepository.save(user);
    }

    @Override
    public UserRegisterResponseDto registerAdmin(UserRegisterRequestDto adminDto) {
        userHelper.checkUserExistAndPasswordEqualAfterRegister(adminDto);
        User user = userRegisterDtoToUserMapper.apply(adminDto, Role.LIBRARIAN);
        userRepository.save(user);
        return userResponseDtoMapper.apply(user);
    }
    @Override
    public String loginUser(UserLoginRequestDto loginRequest) {
        User user = userHelper.findUserByEmailOrThrowNotFoundException(loginRequest.email());
        checkPasswordsMatch(loginRequest.password(), user.getPassword());
        String jwtToken = jwtService.generateToken(user);
        user.setAccessToken(jwtToken);
        user=userRepository.save(user);
        return user.getAccessToken();
    }

    private void checkPasswordsMatch(String pass1,String pass2){
        if (!passwordEncoder.matches(pass1, pass2)) {
            throw new BadCredentialsException("Invalid password");
        }
    }
}
