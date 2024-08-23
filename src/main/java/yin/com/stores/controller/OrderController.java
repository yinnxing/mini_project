package yin.com.stores.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yin.com.stores.auth.JwtUtils;
import yin.com.stores.dto.OrderResponse;
import yin.com.stores.dto.request.OrderRequest;
import yin.com.stores.dto.response.ApiResponse;
import yin.com.stores.service.impl.OrderServiceImp;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/orders")
public class OrderController {
    OrderServiceImp orderService;

    @PostMapping("/{storeId}/placeOrder")
    ApiResponse<OrderResponse> createOrder(@PathVariable String storeId, @RequestBody OrderRequest dto){
        String userId = JwtUtils.getUserId();
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authorities = authentication.getAuthorities();
        authorities.forEach(auth -> System.out.println("Authority: " + auth.getAuthority()));
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.createOrder(storeId, userId, dto))
                .build();
    }

}
