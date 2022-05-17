package swag.rest.bank_app_delivery.entity;


import org.springframework.stereotype.Component;


public abstract class AccountDeposit extends Account {

    public AccountDeposit(AccountType accountType, long id, String clientID, long bankID, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientID, bankID, balance, withdrawAllowed);
    }

    public AccountDeposit() {

    }
}
