package swag.rest.bank_app_delivery.entity;

import lombok.*;



@Getter
@NoArgsConstructor
public abstract class Account {
    private AccountType accountType;
    private long id;
    private String clientID;

    private long bankID;
    private double balance;
    private boolean withdrawAllowed;

    public Account(AccountType accountType, long id, String clientID, long bankID, double balance, boolean withdrawAllowed) {
        this.accountType = accountType;
        this.id = id;
        this.clientID = clientID;
        this.bankID = bankID;
        this.balance = balance;
        this.withdrawAllowed = withdrawAllowed;
    }



}
