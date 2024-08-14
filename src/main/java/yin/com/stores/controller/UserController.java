package yin.com.stores.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import yin.com.stores.dto.request.UserCreationRequest;
import yin.com.stores.dto.request.UserUpdateRequest;
import yin.com.stores.dto.response.ApiResponse;
import yin.com.stores.dto.response.UserResponse;
import yin.com.stores.service.impl.UserServiceImp;

import java.util.List;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/users")
public class UserController {
    UserServiceImp userService;
    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }
    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
       return ApiResponse.<List<UserResponse>>builder()
               .result(userService.getUsers())
               .build();
    }
    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable String userId){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }
    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId){
       userService.deleteUserById(userId);
       return ApiResponse.<String>builder()
               .message("User has been deleted")
               .build();
    }

}
