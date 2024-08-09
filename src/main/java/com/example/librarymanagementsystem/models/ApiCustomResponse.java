package com.example.librarymanagementsystem.models;

import lombok.Builder;

@Builder
public record ApiCustomResponse<T>(String message, T data, Integer statusCode, boolean isSuccessful) {
}
