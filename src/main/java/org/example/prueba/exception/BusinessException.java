package org.example.prueba.exception;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter
public class BusinessException  extends BaseError {

    private final String code;
    private final String entityTypeName;

    public BusinessException(
            @JsonProperty("status") ErrorStatus status,
            @JsonProperty("message") String message,
            @JsonProperty("code") ErrorCode code,
            @JsonProperty("entityTypeName") String entityTypeName) {
        super(status, message);
        this.code = code.toString();
        this.entityTypeName = entityTypeName;
    }

    @JsonCreator
    public BusinessException(
            @JsonProperty("status") ErrorStatus status,
            @JsonProperty("message") String message,
            @JsonProperty("code") String code,
            @JsonProperty("entityTypeName") String entityTypeName) {
        super(status, message);
        this.code = code;
        this.entityTypeName = entityTypeName;
    }
}