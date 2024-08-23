package yin.com.stores.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import yin.com.stores.dto.OrderProductDTO;
import yin.com.stores.model.OrderProduct;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)


    OrderProduct toOrderProduct(OrderProductDTO dto);

    OrderProductDTO toOrderProductDTO(OrderProduct orderProduct);
}
