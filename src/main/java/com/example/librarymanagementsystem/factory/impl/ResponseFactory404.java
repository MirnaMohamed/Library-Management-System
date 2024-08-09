package com.example.librarymanagementsystem.factory.impl;

import com.example.librarymanagementsystem.factory.ResponseFactory;
import com.example.librarymanagementsystem.models.ApiCustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseFactory404 implements ResponseFactory {

    @Override
    public ApiCustomResponse<?> createResponse(Object data, String message) {
        return ApiCustomResponse
                .builder()
                .message(message)
                .data(null)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .isSuccessful(false)
                .build();
    }
}