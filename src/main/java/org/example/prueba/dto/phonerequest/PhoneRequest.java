package org.example.prueba.dto.phonerequest;

public record PhoneRequest(
        String number,
        String cityCode,
        String contryCode
) {
}