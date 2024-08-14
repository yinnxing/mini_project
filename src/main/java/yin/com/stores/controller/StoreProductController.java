package yin.com.stores.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import yin.com.stores.dto.request.StoreProductRequest;
import yin.com.stores.dto.response.ApiResponse;
import yin.com.stores.model.StoreProduct;
import yin.com.stores.service.impl.StoreProductServiceImp;

import java.util.List;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/stores/{storeId}/products")
public class StoreProductController {
    StoreProductServiceImp storeProductService;

    @PostMapping
    ApiResponse<StoreProduct> addProduct(@RequestBody StoreProductRequest dto){
        return ApiResponse.<StoreProduct>builder()
                .result(storeProductService.addProductToStore(dto))
                .build();
    }





}
