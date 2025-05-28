package com.java.planning.mapper;

import com.java.planning.dto.UserDto;
import com.java.planning.dto.UserUpdateDto;
import com.java.planning.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);
    void updateEntityFromUpdateDto(@MappingTarget User user, UserUpdateDto dto);
}