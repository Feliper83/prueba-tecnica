package org.example.prueba.service;


import org.example.prueba.dto.user.UserRequest;
import org.example.prueba.dto.user.UserResponse;
import org.example.prueba.exception.BaseError;

import java.util.Locale;

public interface IUserService {
    UserResponse createUser(UserRequest request, Locale locale,String token) throws BaseError;
}