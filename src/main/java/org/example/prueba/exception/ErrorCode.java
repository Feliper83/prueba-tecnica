package org.example.prueba.exception;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    SUCCESS(20000),
    NO_SPECIFIC_PROBLEM(40000),
    NO_UNIQUE_ENTITY(40027),
    NO_VALID_BUSINESS_RULE(40029),
    VALID_BUT_NOT_COMPLETE(40034),
    CLOUD_ERROR(40037),
    ENTITY_DEPENDENCE(40038),
    SUCCESS_BUT_WARNINGS(40060),
    UNAUTHORIZED(40100),
    FORBIDDEN(40300),
    NOT_FOUND(40400),
    ALREADY_EXISTS(40401),
    UNPROCESSABLE_CONTENT(42200),
    INTERNAL_SERVER_ERROR(50000),
    API_UNEXPECTED_ERROR(50041),
    CLOUD_UNEXPECTED_ERROR(50042),
    EXTERN_SERVICE_UNEXPECTED_ERROR(50043);

    private final int code;

    @JsonValue
    @Override
    public String toString() {
        return String.valueOf(code);
    }
}