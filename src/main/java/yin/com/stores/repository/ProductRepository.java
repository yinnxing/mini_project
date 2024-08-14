package yin.com.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yin.com.stores.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByDeleted(boolean isDeleted);
    boolean existsByName(String name);
}
