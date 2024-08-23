package yin.com.stores.service.impl;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import yin.com.stores.dto.StoreProductDTO;
import yin.com.stores.dto.request.StoreProductRequest;
import yin.com.stores.dto.request.StoreProductUpdateReq;
import yin.com.stores.exception.AppException;
import yin.com.stores.exception.ErrorCode;
import yin.com.stores.mapper.StoreProductMapper;
import yin.com.stores.model.Product;
import yin.com.stores.model.Store;
import yin.com.stores.model.StoreProduct;
import yin.com.stores.repository.ProductRepository;
import yin.com.stores.repository.StoreProductRepository;
import yin.com.stores.repository.StoreRepository;
import yin.com.stores.service.interf.StoreProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoreProductServiceImp implements StoreProductService {
    StoreProductRepository storeProductRepository;
    ProductRepository productRepository;
    StoreRepository storeRepository;
    StoreProductMapper storeProductMapper;


    @Transactional
    public StoreProduct addProductToStore(String storeId, StoreProductRequest dto) {
        Store store = storeRepository.findByStoreIdAndDeletedFalse(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.STORE_NOT_FOUND));

        Product product = productRepository.findByProductIdAndDeletedFalse(dto.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (storeProductRepository.existsByStoreIdAndProductId(storeId, dto.getProductId())) {
            throw new RuntimeException("Product already exists in the store");
        }
        StoreProduct storeProduct = storeProductMapper.toStoreProduct(dto);
        storeProduct.setStoreId(storeId);
        storeProduct.setStore(store);
        storeProduct.setProduct(product);
        storeProductRepository.save(storeProduct);
        return storeProduct;
    }

    @Override
    public List<StoreProductDTO> getStoreProduct(String storeId) {
        Store store = storeRepository.findByStoreIdAndDeletedFalse(storeId)
                .orElseThrow(() -> new AppException(ErrorCode.STORE_NOT_FOUND));
        List<StoreProduct> storeProducts = storeProductRepository.findByStoreIdAndStore_DeletedFalseAndProduct_DeletedFalse(storeId);
        return storeProducts.stream()
               .map(sp -> new StoreProductDTO(sp.getProductId(),
                       sp.getProduct().getName(), sp.getProduct().getPrice(), sp.getStock_quantity()))
               .collect(Collectors.toList());
    }

    public StoreProductDTO updateStoreProduct(String storeId, String productId, StoreProductUpdateReq request){
        StoreProduct sp = storeProductRepository.findByStoreIdAndProductId(storeId, productId);
        sp.setStock_quantity(request.getStock_quantity());
        storeProductRepository.save(sp);
        StoreProductDTO storeProductDTO = new StoreProductDTO(sp.getProductId(),
                sp.getProduct().getName(), sp.getProduct().getPrice(), sp.getStock_quantity());
        return storeProductDTO;

    }

    @Override
    public void deleteStoreProduct(String productId) {

    }


}




