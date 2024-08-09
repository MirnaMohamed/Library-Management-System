package com.example.librarymanagementsystem.helper;


import com.example.librarymanagementsystem.dto.request.UserRegisterRequestDto;
import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.exceptions.*;
import com.example.librarymanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

@Component
@RequiredArgsConstructor
public class UserHelper {

    private final UserRepository userRepository;
    public User findUserByIdOrThrowNotFoundException(Long userId){
        return userRepository.findById(userId).orElseThrow(
                ()-> new NotFoundException("user with id : "+userId+" not found!")
        );
    }
    public User findUserByEmailOrThrowNotFoundException(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }

    public void checkUserExistAndPasswordEqualAfterRegister(UserRegisterRequestDto userRegisterDto) {
        if (userRepository.existsByEmail(userRegisterDto.email())) {
            throw new EmailAlreadyExistException("Email address already exists.");
        }
        if(!userRegisterDto.password().equals(userRegisterDto.confirmPassword())){
            throw new PasswordMismatchException("The passwords entered don't match. Please try again.");
        }
    }
}

