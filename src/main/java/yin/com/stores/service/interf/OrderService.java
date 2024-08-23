package yin.com.stores.service.interf;

import yin.com.stores.dto.OrderResponse;
import yin.com.stores.dto.request.OrderRequest;

public interface OrderService {
    public OrderResponse createOrder(String storeId, String userId, OrderRequest dto);
}
