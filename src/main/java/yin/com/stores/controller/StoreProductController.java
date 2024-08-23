package yin.com.stores.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import yin.com.stores.dto.StoreProductDTO;
import yin.com.stores.dto.request.StoreProductRequest;
import yin.com.stores.dto.request.StoreProductUpdateReq;
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
    ApiResponse<StoreProduct> addProduct(@PathVariable String storeId, @RequestBody StoreProductRequest dto){
        return ApiResponse.<StoreProduct>builder()
                .result(storeProductService.addProductToStore(storeId, dto))
                .build();
    }
    @GetMapping
    ApiResponse<List<StoreProductDTO>> getStoreProducts(@PathVariable String storeId){
        return ApiResponse.<List<StoreProductDTO>>builder()
                .result(storeProductService.getStoreProduct(storeId))
                .build();
    }
    @PutMapping("/{productId}")
    ApiResponse<StoreProductDTO> updateStoreProduct(@PathVariable String storeId, @PathVariable String productId,
                                                    @RequestBody StoreProductUpdateReq req){
        return ApiResponse.<StoreProductDTO>builder()
                .result(storeProductService.updateStoreProduct(storeId, productId, req))
                .build();
    }







}
