package yin.com.stores.controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yin.com.stores.dto.response.ApiResponse;
import yin.com.stores.dto.response.UserResponse;
import yin.com.stores.model.User;
import yin.com.stores.service.impl.UserServiceImp;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/stores/{storeId}/users")
public class UserStoreController {
    UserServiceImp userService;
    @GetMapping
    ApiResponse<List<UserResponse>> getEmployeesByStore(@PathVariable String storeId){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getEmployeesByStore(storeId))
                .build();
    }

}
