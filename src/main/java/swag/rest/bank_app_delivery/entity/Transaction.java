package swag.rest.bank_app_delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    String accountID;
    String clientID;
    String transaction;
    double currentBalance;
}
