package yin.com.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yin.com.stores.model.StoreProduct;
import yin.com.stores.model.enum_key.StoreProductId;

import java.util.List;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, StoreProductId> {
    boolean existsByStoreIdAndProductId(String storeId, String productId);
    List<StoreProduct> findByStoreIdAndStore_DeletedFalseAndProduct_DeletedFalse(String storeId);
    StoreProduct findByStoreIdAndProductId(String storeId, String productId);



}
