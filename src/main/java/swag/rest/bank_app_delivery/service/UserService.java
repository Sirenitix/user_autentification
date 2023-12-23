package swag.rest.bank_app_delivery.service;

import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.entity.Users;

import java.util.Optional;

@Service
public interface UserService {
    public Users save(Users user);
    public Optional<Users> findByUsername(String username);
    public Optional<Users> findById(Integer id);

    Users changePassword(String newPassword);
}
