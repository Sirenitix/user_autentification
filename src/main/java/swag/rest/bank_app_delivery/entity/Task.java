package swag.rest.bank_app_delivery.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "task")
public class Task {

    @Id
    private Long id;

    private Long userId;

    private String name;

    private String description;
}
