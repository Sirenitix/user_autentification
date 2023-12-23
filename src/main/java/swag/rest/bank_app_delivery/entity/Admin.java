package swag.rest.bank_app_delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Admin {
    String username;
    Boolean isMaster;
}
