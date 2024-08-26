package yin.com.stores.service.interf;

import yin.com.stores.dto.request.UserCreationRequest;
import yin.com.stores.dto.request.UserUpdateRequest;
import yin.com.stores.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    public UserResponse createUser(UserCreationRequest request);
    public List<UserResponse> getUsers();
    public UserResponse getUser(String userId);
    public UserResponse updateUser(String id, UserUpdateRequest request);
    public void deleteUserById(String userId);
    public List<UserResponse> getEmployeesByStore(String storeId, String role);
}
