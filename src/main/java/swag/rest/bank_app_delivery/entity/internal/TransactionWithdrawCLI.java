package swag.rest.bank_app_delivery.entity.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.service.*;
import swag.rest.bank_app_delivery.service.internal.DBService;

@Component
public class TransactionWithdrawCLI {
    TransactionWithdraw transactionWithdraw;
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    AccountListingService accountListing;
    @Autowired
    DBService dbService;

    public TransactionWithdrawCLI(TransactionWithdraw transactionWithdraw, WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI, AccountListingService accountListingService) {
        this.transactionWithdraw = transactionWithdraw;
        this.withdrawDepositOperationCLIUI = withdrawDepositOperationCLIUI;
        this.accountListing = accountListingService;
    }

    public void withdrawMoney(String clientID) {
        System.out.println("Type account ID");
        String accountID = withdrawDepositOperationCLIUI.requestClientAmountNumber();
        AccountWithdraw accountWithdraw = (AccountWithdraw) dbService.getClientAccountById(Integer.parseInt(accountID.charAt(accountID.length() - 1)+""));
        System.out.println("Type Amount of money");
        double amount = withdrawDepositOperationCLIUI.requestClientAmount();
        transactionWithdraw.execute(accountWithdraw, amount);
    }
}
