package org.example.prueba.controller;

import jakarta.validation.Valid;
import org.example.prueba.dto.user.UserRequest;
import org.example.prueba.dto.user.UserResponse;
import org.example.prueba.exception.BaseError;
import org.example.prueba.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestHeader("Authorization") String token,
                                                   @RequestBody @Valid UserRequest request,
                                                   Locale locale) throws BaseError {
        UserResponse user = service.createUser(request, locale, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
