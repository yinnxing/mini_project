package yin.com.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yin.com.stores.model.Store;
import yin.com.stores.model.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    Optional<Store> findByName(String name);
    List<Store> findAllByDeleted(boolean isDeleted);
    Optional<Store> findByStoreIdAndDeletedFalse(String storeId);

}
