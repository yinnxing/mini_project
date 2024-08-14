package yin.com.stores.mapper;

import org.mapstruct.Mapper;
import yin.com.stores.dto.request.StoreRequest;
import yin.com.stores.dto.response.StoreResponse;
import yin.com.stores.model.Store;

@Mapper(componentModel = "spring")

public interface StoreMapper {
    Store toStore(StoreRequest request);
    StoreResponse toStoreResponse(Store store);
}
