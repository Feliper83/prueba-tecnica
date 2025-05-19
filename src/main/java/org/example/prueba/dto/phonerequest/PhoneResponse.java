package org.example.prueba.dto.phonerequest;

public record PhoneResponse(
        Long id,
        String number,
        String citycode,
        String contrycode
) {
}