package swag.rest.aster.todo.service;

import org.springframework.stereotype.Service;
import swag.rest.aster.todo.entity.Users;

import java.util.Optional;

@Service
public interface UserService {
    public Users save(Users user);
    public Optional<Users> findByUsername(String username);
    public Optional<Users> findById(Integer id);

    Users changePassword(String newPassword);
}
