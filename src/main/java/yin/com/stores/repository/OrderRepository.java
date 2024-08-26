package yin.com.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yin.com.stores.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByStore_StoreId(String storeId);
}
