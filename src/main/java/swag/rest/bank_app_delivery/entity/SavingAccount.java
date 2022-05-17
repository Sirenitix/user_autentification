package swag.rest.bank_app_delivery.entity;

import lombok.NoArgsConstructor;


public class SavingAccount extends AccountWithdraw{
    public SavingAccount(AccountType accountType, long id, String clientID, long bankID, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientID, bankID, balance, withdrawAllowed);
    }

    public SavingAccount() {

    }

    @Override
    public String toString() {
        return String.format("Account{id='%03d%06d', clientID='%s', balance=%s, type='%s'}", super.getId(), super.getBankID(), super.getClientID(),super.getBalance(), super.getAccountType());
    }
}
