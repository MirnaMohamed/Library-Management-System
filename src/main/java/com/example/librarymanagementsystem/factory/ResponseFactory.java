package com.example.librarymanagementsystem.factory;

import com.example.librarymanagementsystem.models.ApiCustomResponse;

public interface ResponseFactory<T> {
    ApiCustomResponse<?> createResponse(T data, String message);
}
