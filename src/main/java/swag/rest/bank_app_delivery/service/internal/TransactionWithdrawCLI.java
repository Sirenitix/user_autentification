package swag.rest.bank_app_delivery.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.service.*;
import swag.rest.bank_app_delivery.service.DBService;

@Component
public class TransactionWithdrawCLI {
    TransactionWithdraw transactionWithdraw;
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    AccountListingService accountListing;


    @Qualifier("DBServiceImpl")
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
        AccountWithdraw accountWithdraw = (AccountWithdraw) dbService.getClientAccountById(Integer.parseInt(accountID) - 1000000, Integer.parseInt(clientID));
        System.out.println("Type Amount of money");
        double amount = withdrawDepositOperationCLIUI.requestClientAmount();
        transactionWithdraw.execute(accountWithdraw, amount, Integer.parseInt(clientID));
    }
}
