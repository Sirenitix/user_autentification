package swag.rest.aster.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Admin {
    String username;
    Boolean isMaster;
}
