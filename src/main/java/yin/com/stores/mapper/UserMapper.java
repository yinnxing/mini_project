package yin.com.stores.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import yin.com.stores.dto.request.UserCreationRequest;
import yin.com.stores.dto.request.UserUpdateRequest;
import yin.com.stores.dto.response.UserResponse;
import yin.com.stores.model.User;

@Mapper(componentModel = "spring")

public interface UserMapper {
    @Mapping(target = "stores", ignore = true)
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    @Mapping(target = "stores", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
