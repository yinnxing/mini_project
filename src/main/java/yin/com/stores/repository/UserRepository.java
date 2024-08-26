package yin.com.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yin.com.stores.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByUsernameAndDeletedFalse(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByIdAndDeletedFalse(String userId);


    List<User> findAllByDeleted(boolean isDeleted);

   @Query(value = "SELECT u.* FROM User u JOIN user_stores us ON u.id = us.user_id " +
                "WHERE us.stores_store_id = :storeId AND u.deleted = false " +
                "AND u.role = 'MANAGER'", nativeQuery = true)
   User findManagerByStoreId(@Param("storeId") String storeId);
   List<User> findByStores_StoreIdAndRoleAndDeletedFalse(String storeId, String role);




}
