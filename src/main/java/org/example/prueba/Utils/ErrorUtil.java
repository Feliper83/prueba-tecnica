package org.example.prueba.Utils;

import lombok.RequiredArgsConstructor;
import org.example.prueba.exception.BusinessException;
import org.example.prueba.exception.ErrorCode;
import org.example.prueba.exception.ErrorStatus;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ErrorUtil {

    private final MessageSource messageSource;

    public BusinessException businessError(String errorKey, Class<?> entityClass, Locale locale) {
        return new BusinessException(
                ErrorStatus.BAD_REQUEST,
                messageSource.getMessage(errorKey, null, locale),
                ErrorCode.NO_VALID_BUSINESS_RULE,
                entityClass.getSimpleName());
    }

    public BusinessException notFoundError(String errorKey, Class<?> entityClass, Locale locale) {
        return new BusinessException(
                ErrorStatus.NOT_FOUND,
                messageSource.getMessage(errorKey, null, locale),
                ErrorCode.NOT_FOUND,
                entityClass.getSimpleName()
        );
    }

    public BusinessException internalError(String errorKey, Class<?> entityClass, Locale locale) {
        return new BusinessException(
                ErrorStatus.INTERNAL_SERVER_ERROR,
                messageSource.getMessage(errorKey,null, locale),
                ErrorCode.INTERNAL_SERVER_ERROR,
                entityClass.getSimpleName());
    }
}
