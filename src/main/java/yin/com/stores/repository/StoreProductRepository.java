package yin.com.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yin.com.stores.model.StoreProduct;
@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct,String> {
    boolean existsByStoreIdAndProductId(String storeId, String productId);

}
