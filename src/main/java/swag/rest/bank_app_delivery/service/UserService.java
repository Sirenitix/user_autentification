package swag.rest.bank_app_delivery.service;

import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.entity.User;

import java.util.Optional;

@Service
public interface UserService {
    public User save(User user);
    public Optional<User> findByUsername(String username);
    public Optional<User> findById(Integer id);


}
