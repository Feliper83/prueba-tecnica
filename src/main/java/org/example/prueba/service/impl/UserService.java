package org.example.prueba.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.prueba.Utils.ActivationTokenUtils;
import org.example.prueba.Utils.ErrorUtil;
import org.example.prueba.dto.user.UserRequest;
import org.example.prueba.dto.user.UserResponse;
import org.example.prueba.entity.Phone;
import org.example.prueba.entity.User;
import org.example.prueba.exception.BaseError;
import org.example.prueba.mapper.UserMapper;
import org.example.prueba.repository.UserRepository;
import org.example.prueba.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    @Value("${password.regex}")
    private String passwordRegex;

    private final UserRepository repository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final ErrorUtil errorUtil;

    private static final String ERROR_USER_ALREADY_EXISTS = "error.email_exists";
    private static final String ERROR_INVALID_PASSWORD = "error.invalid_password";
    private static final String ERROR_USER_CREATION_FAILED = "error.user_creation_failed";

    @Override
    public UserResponse createUser(UserRequest request, Locale locale,String token) throws BaseError {
        if (repository.findByEmail(request.email()).isPresent()) {
            throw errorUtil.businessError(ERROR_USER_ALREADY_EXISTS, User.class, locale);
        }

        if (!request.password().matches(passwordRegex)) {
            throw errorUtil.businessError(ERROR_INVALID_PASSWORD, User.class, locale);
        }

        User user;
        try {

            List<Phone> phones =  userMapper.toEntity(request).getPhones().stream()
                    .map(req -> Phone.builder()
                            .number(req.getNumber())
                            .cityCode(req.getCityCode())
                            .contryCode(req.getContryCode())
                            .build()
                    ).toList();

            user = userMapper.toEntity(request).toBuilder()
                    .token(token)
                    .phones(phones)
                    .created(LocalDateTime.now())
                    .modified(LocalDateTime.now())
                    .token(ActivationTokenUtils.createActivationToken())
                    .build();
        } catch (Exception e) {
            log.error("Error creating user entity", e);
            throw errorUtil.businessError(ERROR_USER_CREATION_FAILED, User.class, locale);
        }

        User savedUser = repository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        return userMapper.toDto(savedUser);
    }
}

