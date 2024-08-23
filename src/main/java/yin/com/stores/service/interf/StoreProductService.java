package yin.com.stores.service.interf;

import yin.com.stores.dto.StoreProductDTO;
import yin.com.stores.dto.request.StoreProductRequest;
import yin.com.stores.dto.request.StoreProductUpdateReq;
import yin.com.stores.model.StoreProduct;

import java.util.List;

public interface StoreProductService {
    public StoreProduct addProductToStore(String storeId, StoreProductRequest dto);

    public List<StoreProductDTO> getStoreProduct(String storeId);

    public void deleteStoreProduct(String productId);


    public StoreProductDTO updateStoreProduct(String storeId, String productId, StoreProductUpdateReq req);

}
