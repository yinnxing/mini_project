package yin.com.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yin.com.stores.model.Store;

import java.util.List;
import java.util.Optional;
@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    Optional<Store> findByName(String name);
    List<Store> findAllByDeleted(boolean isDeleted);
    Optional<Store> findByStoreIdAndDeletedFalse(String storeId);
    @Query(value = "SELECT s.* FROM Store s JOIN user_stores us ON us.stores_store_id = s.store_id Where us.user_id = :userId AND s.deleted = false"
    , nativeQuery = true)
    List<Store> findStoresByUserIdAndDeletedFalse(@Param("userId") String userId);


}
