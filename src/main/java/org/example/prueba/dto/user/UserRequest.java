package org.example.prueba.dto.user;

import lombok.Builder;
import org.example.prueba.dto.phonerequest.PhoneRequest;

import java.util.List;

@Builder(toBuilder = true)
public record UserRequest(
        String name,
        String email,
        String password,
        List<PhoneRequest> phones
) {
}