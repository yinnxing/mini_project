package yin.com.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yin.com.stores.model.OrderProduct;
import yin.com.stores.model.enum_key.OrderProductId;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
