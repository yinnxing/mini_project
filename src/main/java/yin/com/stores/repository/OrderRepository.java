package yin.com.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yin.com.stores.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
}
