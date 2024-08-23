package yin.com.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yin.com.stores.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByDeleted(boolean isDeleted);
    boolean existsByName(String name);
    Optional<Product> findByProductIdAndDeletedFalse(String productId);
    Optional<Product> findByNameAndDeletedFalse(String productId);

}
