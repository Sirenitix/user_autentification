package swag.rest.bank_app_delivery.entity.internal;

import org.springframework.stereotype.Component;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.service.*;

@Component
public class TransactionWithdrawCLI {
    TransactionWithdraw transactionWithdraw;
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    AccountListingService accountListing;

    public TransactionWithdrawCLI(TransactionWithdraw transactionWithdraw, WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI, AccountListingService accountListingService) {
        this.transactionWithdraw = transactionWithdraw;
        this.withdrawDepositOperationCLIUI = withdrawDepositOperationCLIUI;
        this.accountListing = accountListingService;
    }

    public void withdrawMoney(String clientID) {
        System.out.println("Type account ID");
        AccountWithdraw accountWithdraw = accountListing.getClientWithdrawAccount(clientID,withdrawDepositOperationCLIUI.requestClientAmountNumber());
        System.out.println("Type Amount of money");
        double amount = withdrawDepositOperationCLIUI.requestClientAmount();
        transactionWithdraw.execute(accountWithdraw, amount);
    }
}
