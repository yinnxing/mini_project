package yin.com.stores.service.interf;
import yin.com.stores.dto.ProductDTO;
import yin.com.stores.dto.request.StoreProductRequest;
import yin.com.stores.model.StoreProduct;

import java.util.List;

public interface StoreProductService {
    public StoreProduct addProductToStore(StoreProductRequest dto);
}
