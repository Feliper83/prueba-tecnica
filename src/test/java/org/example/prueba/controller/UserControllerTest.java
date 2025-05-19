package org.example.prueba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.prueba.Utils.ErrorUtil;
import org.example.prueba.dto.phonerequest.PhoneRequest;
import org.example.prueba.dto.user.UserRequest;
import org.example.prueba.dto.user.UserResponse;
import org.example.prueba.entity.Phone;
import org.example.prueba.entity.User;
import org.example.prueba.mapper.UserMapper;
import org.example.prueba.repository.UserRepository;
import org.example.prueba.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({
        UserController.class,
        IUserService.class
})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private ErrorUtil errorUtil;

    UserRequest request = null;

    @Autowired
    private IUserService userService;



    @Mock
    private UserRepository repository;

    User user = null;
    @MockitoBean
    private UserMapper userMapper;

    @BeforeEach
    void setup() {


        user = User.builder()
                .id(UUID.randomUUID())
                .name("John Doe")
                .email("john.doe@example.com")
                .password("abcdefg1")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("sampleToken123")
                .isActive(true)
                .phones(Collections.singletonList(Phone.builder()
                        .id(1L)
                        .number("123456789")
                        .cityCode("1")
                        .contryCode("57")
                        .build()))
                .build();
        
        request = UserRequest.builder()
                .name("John Doe")
                .email("john@example.com")
                .password("abcdefg1")
                .phones(Collections.singletonList(new PhoneRequest("123456789", "1", "57")))
                .build();


    }

    @Test
    void testCreateUser_Success() throws Exception {
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        final String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        ReflectionTestUtils.setField(userService, "passwordRegex", passwordRegex);

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(
                new UserResponse(user.getId(), user.getCreated(), user.getModified(),
                        null, user.getToken(), true));


        mockMvc.perform(post("/api/users", UUID.randomUUID())
                        .header("Accept-Language", "es")
                        .header("Authorization", "asdsadsadasd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.token").exists());
    }
}
