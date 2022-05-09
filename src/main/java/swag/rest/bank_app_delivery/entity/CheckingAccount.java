package swag.rest.bank_app_delivery.entity;

import org.springframework.stereotype.Component;


public class CheckingAccount extends AccountWithdraw {

    public CheckingAccount(AccountType accountType, long id, String clientID, long bankID, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientID, bankID, balance, withdrawAllowed);
    }

    @Override
    public String toString() {
        return String.format("Account{id='%03d%06d', clientID='%s', balance=0.0, type='%s'}", super.getId(), super.getBankID(), super.getClientID(), super.getAccountType());
    }
}
