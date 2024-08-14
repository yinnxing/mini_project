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
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);

    List<User> findAllByDeleted(boolean isDeleted);

    @Query(value = "SELECT u.* FROM User u JOIN user_stores us ON u.id = us.user_id " +
            "JOIN Store s ON us.stores_id = s.id WHERE s.id = :storeId AND u.deleted = false " +
            "AND u.role = 'EMPLOYEE'", nativeQuery = true)
    List<User> findUsersByStoreIdAndRole(@Param("storeId") String storeId);



}
