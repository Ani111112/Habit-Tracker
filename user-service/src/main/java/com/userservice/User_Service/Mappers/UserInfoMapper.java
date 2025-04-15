package com.userservice.User_Service.Mappers;

import com.userservice.User_Service.dto.response.UserInfoResponse;
import com.userservice.User_Service.model.ApplicationUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserInfoMapper {
    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

    UserInfoResponse map(ApplicationUser applicationUser);
}
