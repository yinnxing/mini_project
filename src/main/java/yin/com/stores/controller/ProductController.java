package yin.com.stores.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import yin.com.stores.dto.ProductDTO;
import yin.com.stores.dto.response.ApiResponse;
import yin.com.stores.service.impl.ProductServiceImp;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/products")
public class ProductController {
    ProductServiceImp productService;
    @GetMapping
    ApiResponse<List<ProductDTO>> getProducts(){
        return ApiResponse.<List<ProductDTO>>builder()
                .result(productService.getProducts())
                .build();
    }
    @GetMapping("/{productId}")
    ApiResponse<ProductDTO> getProductById(@PathVariable String productId){
        return ApiResponse.<ProductDTO>builder()
                .result(productService.getProductById(productId))
                .build();
    }
    @PutMapping("/{productId}")
    ApiResponse<ProductDTO> updateProduct(@PathVariable String productId, @RequestBody ProductDTO dto){
        return ApiResponse.<ProductDTO>builder()
                .result(productService.updateProduct(productId, dto))
                .build();

    }

    //@GetMapping("/{productName}")
    //ApiResponse<ProductDTO> getProductByName(@PathVariable String productName){
    //    return ApiResponse.<ProductDTO>builder()
    //            .result(productService.getProductByName(productName))
    //            .build();
    //}



    @PostMapping
    ApiResponse<ProductDTO> createProduct(@RequestBody ProductDTO dto){
        return ApiResponse.<ProductDTO>builder()
                .result(productService.createProduct(dto))
                .build();

    }
    @DeleteMapping("/{productId}")
    ApiResponse<String> deleteProduct(@PathVariable String productId){
      productService.deleteProduct(productId);
      return ApiResponse.<String>builder()
              .message("Product has been deleted")
              .build();
    }


}
