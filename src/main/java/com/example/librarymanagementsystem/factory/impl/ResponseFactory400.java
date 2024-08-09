package com.example.librarymanagementsystem.factory.impl;

import com.example.librarymanagementsystem.factory.ResponseFactory;
import com.example.librarymanagementsystem.models.ApiCustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseFactory400 implements ResponseFactory {
    @Override
    public ApiCustomResponse<?> createResponse(Object data, String message) {
        return ApiCustomResponse
                .builder()
                .message(message)
                .data(null)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build();
    }
}
