package swag.rest.bank_app_delivery.service;

import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.entity.*;

@Service
public interface AccountCreationService {
    void create(AccountType accountType, long bankID, String clientID, long accountID);
}
