package yin.com.stores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yin.com.stores.model.InvalidedToken;

@Repository
public interface InvalidedTokenRepository extends JpaRepository<InvalidedToken, String> {
}
