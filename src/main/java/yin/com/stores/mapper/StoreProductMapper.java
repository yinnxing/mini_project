package yin.com.stores.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import yin.com.stores.dto.request.StoreProductRequest;
import yin.com.stores.model.StoreProduct;

@Mapper(componentModel = "spring")

public interface StoreProductMapper {
    StoreProductRequest toStoreProductDTO(StoreProduct storeProduct);
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "store", ignore = true)
    @Mapping(target = "storeId", ignore = true)
    StoreProduct toStoreProduct(StoreProductRequest dto);
}
