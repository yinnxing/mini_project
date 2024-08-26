package yin.com.stores.service.interf;

import yin.com.stores.dto.response.OrderResponse;
import yin.com.stores.dto.request.OrderRequest;
import yin.com.stores.model.Order;

import java.util.List;

public interface OrderService {
    public OrderResponse createOrder(String storeId, String userId, OrderRequest dto);
    public List<OrderResponse> getStoreOrders(String storeId);
}
