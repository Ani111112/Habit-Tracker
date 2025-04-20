package com.userservice.User_Service.Mappers;

import com.userservice.User_Service.dto.response.UserResponse;
import com.userservice.User_Service.model.ApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserResponseMapper {
    UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);

    UserResponse map(ApplicationUser applicationUser);
}
