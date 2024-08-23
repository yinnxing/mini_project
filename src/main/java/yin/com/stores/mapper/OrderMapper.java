package yin.com.stores.mapper;

import org.mapstruct.Mapper;
import yin.com.stores.dto.OrderResponse;
import yin.com.stores.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);
}
