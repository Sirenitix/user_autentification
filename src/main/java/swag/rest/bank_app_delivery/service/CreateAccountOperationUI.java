package swag.rest.bank_app_delivery.service;

import org.springframework.stereotype.Component;
import swag.rest.bank_app_delivery.entity.AccountType;

public interface CreateAccountOperationUI {
    AccountType requestAccountType();
}
