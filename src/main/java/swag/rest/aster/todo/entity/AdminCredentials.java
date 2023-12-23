package swag.rest.aster.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminCredentials  {
    String username;
    Boolean isMaster;


}
