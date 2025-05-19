package org.example.prueba.mapper;


import org.example.prueba.dto.user.UserRequest;
import org.example.prueba.dto.user.UserResponse;
import org.example.prueba.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity (UserRequest userRequest);
    UserResponse toDto (User userRequest);
}