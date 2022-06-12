package swag.rest.bank_app_delivery.entity;

import org.springframework.stereotype.Component;
import swag.rest.bank_app_delivery.dao.TransactionDAO;
import swag.rest.bank_app_delivery.service.AccountWithdrawService;

@Component
public class TransactionWithdraw {
    AccountWithdrawService accountWithdrawService;
    TransactionDAO transactionDAO;

    public TransactionWithdraw(AccountWithdrawService accountWithdrawService, TransactionDAO transactionDAO) {
        this.accountWithdrawService = accountWithdrawService;
        this.transactionDAO = transactionDAO;
    }

    public void execute(AccountWithdraw accountWithdraw, double amount, int clientID) {
        accountWithdrawService.withdraw(amount, accountWithdraw,clientID);
    }
}
