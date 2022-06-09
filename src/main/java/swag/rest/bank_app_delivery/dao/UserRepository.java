package swag.rest.bank_app_delivery.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import swag.rest.bank_app_delivery.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository {
    Optional<User> findByUsername(String username);
}
