package yin.com.stores.service.interf;

import yin.com.stores.dto.request.StoreRequest;
import yin.com.stores.dto.response.StoreResponse;
import yin.com.stores.model.Store;

import java.util.List;

public interface StoreService {
    public List<StoreResponse> getStores();
    public StoreResponse createStore(StoreRequest request);

    public void deleteStore(String storeId);
}
