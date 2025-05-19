package org.example.prueba.service.impl;

import org.example.prueba.Utils.ErrorUtil;
import org.example.prueba.dto.phonerequest.PhoneRequest;
import org.example.prueba.dto.user.UserRequest;
import org.example.prueba.dto.user.UserResponse;
import org.example.prueba.entity.User;
import org.example.prueba.exception.BaseError;
import org.example.prueba.mapper.UserMapper;
import org.example.prueba.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestPropertySource(properties = "password.regex=^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Value("${password.regex}")
    private String passwordRegex;

    @Mock
    private UserRepository repository;

    @Mock
    private ErrorUtil errorUtil;

    @Mock
    private UserMapper userMapper;

    UserRequest request;
    User userEntity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        request = UserRequest.builder()
                .name("John")
                .email("john@example.com")
                .password("abcdefg1")
                .phones(Collections.singletonList(new PhoneRequest( "1", "57","123456")))
                .build();

        userEntity = User.builder()
                .id(UUID.randomUUID())
                .name("John")
                .email("john@example.com")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .token("dummy-token")
                .build();


    }

    @Test
    void createUser_shouldReturnUserResponse() throws BaseError {

        when(repository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        when(userMapper.toEntity(any(UserRequest.class))).thenReturn(userEntity);
        when(repository.save(any(User.class))).thenReturn(userEntity);

        ReflectionTestUtils.setField(userService, "passwordRegex", "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");


        when(userMapper.toDto(userEntity)).thenReturn(
                new UserResponse(userEntity.getId(), userEntity.getCreated(), userEntity.getModified(),
                        null, userEntity.getToken(), true));

        UserResponse response = userService.createUser(request, Locale.ENGLISH,"asdasd");

        assertNotNull(response);
        assertEquals("dummy-token", response.token());
    }
}
