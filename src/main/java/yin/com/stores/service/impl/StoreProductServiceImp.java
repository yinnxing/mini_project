package yin.com.stores.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import yin.com.stores.dto.request.StoreProductRequest;
import yin.com.stores.exception.AppException;
import yin.com.stores.exception.ErrorCode;
import yin.com.stores.mapper.ProductMapper;
import yin.com.stores.mapper.StoreProductMapper;
import yin.com.stores.model.Product;
import yin.com.stores.model.Store;
import yin.com.stores.model.StoreProduct;
import yin.com.stores.repository.ProductRepository;
import yin.com.stores.repository.StoreProductRepository;
import yin.com.stores.repository.StoreRepository;
import yin.com.stores.service.interf.StoreProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoreProductServiceImp implements StoreProductService {
    StoreProductRepository storeProductRepository;
    ProductRepository productRepository;
    StoreRepository storeRepository;
    StoreProductMapper storeProductMapper;


    @Transactional
    public StoreProduct addProductToStore(StoreProductRequest dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new AppException(ErrorCode.STORE_NOT_FOUND));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (storeProductRepository.existsByStoreIdAndProductId(dto.getStoreId(), dto.getProductId())) {
            throw new RuntimeException("Product already exists in the store");
        }
        StoreProduct storeProduct = storeProductMapper.toStoreProduct(dto);
        storeProduct.setStore(store);
        storeProduct.setProduct(product);
        storeProductRepository.save(storeProduct);
        return storeProduct;
    }


}




