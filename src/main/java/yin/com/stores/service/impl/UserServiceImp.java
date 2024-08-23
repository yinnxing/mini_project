package yin.com.stores.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yin.com.stores.dto.request.UserCreationRequest;
import yin.com.stores.dto.request.UserUpdateRequest;
import yin.com.stores.dto.response.UserResponse;
import yin.com.stores.exception.AppException;
import yin.com.stores.exception.ErrorCode;
import yin.com.stores.mapper.UserMapper;
import yin.com.stores.model.Store;
import yin.com.stores.model.User;
import yin.com.stores.repository.StoreRepository;
import yin.com.stores.repository.UserRepository;
import yin.com.stores.service.interf.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImp implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    StoreRepository storeRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Store> stores = request.getStores().stream()
                .map(name -> storeRepository.findByName(name)
                        .orElseThrow(() -> new AppException(ErrorCode.STORE_NOT_FOUND)))
                .collect(Collectors.toSet());
        user.setStores(stores);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAllByDeleted(false);
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse getUser(String userId) {
        User user = userRepository.findByIdAndDeletedFalse(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXIST));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXIST)
        );
       userMapper.updateUser(user, request);
       user.setPassword(passwordEncoder.encode(request.getPassword()));
       userRepository.save(user);
       return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUserById(String userId) {
        User user = userRepository.findByIdAndDeletedFalse(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXIST)
        );
        user.setDeleted(true);
        userRepository.save(user);
    }
    @PostAuthorize("hasRole('MANAGER')")
    @Override
    public List<UserResponse> getEmployeesByStore(String storeId) {
        List<User> users = userRepository.findUsersByStoreIdAndRole(storeId);
        return users.stream().map(userMapper::toUserResponse).collect(Collectors.toList());
    }
}
