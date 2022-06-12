package swag.rest.bank_app_delivery.entity;

import org.springframework.stereotype.Component;
import swag.rest.bank_app_delivery.dao.TransactionDAO;
import swag.rest.bank_app_delivery.service.AccountDepositService;

@Component
public class TransactionDeposit {

    AccountDepositService accountDepositService;
    TransactionDAO transactionDAO;

    public TransactionDeposit(AccountDepositService accountDepositService, TransactionDAO transactionDAO) {
        this.accountDepositService = accountDepositService;
        this.transactionDAO = transactionDAO;
    }

    public void execute(Account accountWithdraw, double amount, int clientID) {
        accountDepositService.deposit(amount, accountWithdraw, clientID);
    }
}
