package com.example.librarymanagementsystem.dto.request;

import com.example.librarymanagementsystem.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserRegisterRequestDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String confirmPassword,
        @NotBlank Gender gender
) {
}
