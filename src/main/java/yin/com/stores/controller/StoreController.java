package yin.com.stores.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import yin.com.stores.dto.request.StoreRequest;
import yin.com.stores.dto.response.ApiResponse;
import yin.com.stores.dto.response.StoreResponse;
import yin.com.stores.service.impl.StoreServiceImp;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/stores")
public class StoreController {
    StoreServiceImp storeService;
    @PostMapping
    ApiResponse<StoreResponse> createStore(@RequestBody StoreRequest request){
        return ApiResponse.<StoreResponse>builder()
                .result(storeService.createStore(request))
                .build();

    }
    @GetMapping
    ApiResponse<List<StoreResponse>> getStores(){
        return ApiResponse.<List<StoreResponse>>builder()
                .result(storeService.getStores())
                .build();
    }
    @DeleteMapping("/{storeId}")
    ApiResponse<Void> deleteStore(@PathVariable String storeId){
        storeService.deleteStore(storeId);
        return ApiResponse.<Void>builder().build();
    }


}
