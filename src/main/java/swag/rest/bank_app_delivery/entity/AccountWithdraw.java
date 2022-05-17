package swag.rest.bank_app_delivery.entity;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
public abstract class AccountWithdraw extends Account {

    public AccountWithdraw(AccountType accountType, long id, String clientID, long bankID, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientID, bankID, balance, withdrawAllowed);
    }

}
