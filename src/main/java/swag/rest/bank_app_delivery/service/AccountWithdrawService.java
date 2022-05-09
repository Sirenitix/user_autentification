package swag.rest.bank_app_delivery.service;

import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.entity.AccountWithdraw;

@Service
public interface AccountWithdrawService {
    void withdraw(double amount, AccountWithdraw accountWithdraw);
}
