package swag.rest.bank_app_delivery.service;

import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.entity.User;

@Service
public interface UserService {
    public User save(User user);
}
