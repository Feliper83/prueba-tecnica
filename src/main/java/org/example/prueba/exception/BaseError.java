package org.example.prueba.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class BaseError extends RuntimeException {
    private final ErrorStatus status;
    private final String message;
}
