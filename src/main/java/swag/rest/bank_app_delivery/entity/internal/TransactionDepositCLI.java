package swag.rest.bank_app_delivery.entity.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import swag.rest.bank_app_delivery.entity.Account;
import swag.rest.bank_app_delivery.entity.AccountWithdraw;
import swag.rest.bank_app_delivery.entity.TransactionDeposit;
import swag.rest.bank_app_delivery.entity.WithdrawDepositOperationCLIUI;
import swag.rest.bank_app_delivery.service.AccountListingService;
import swag.rest.bank_app_delivery.service.internal.DBService;

@Component
public class TransactionDepositCLI {
    TransactionDeposit transactionDeposit;
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    AccountListingService accountListing;
    @Autowired
    DBService dbService;

    public TransactionDepositCLI(TransactionDeposit transactionDeposit, WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI, AccountListingService accountListingService) {
        this.transactionDeposit = transactionDeposit;
        this.withdrawDepositOperationCLIUI = withdrawDepositOperationCLIUI;
        this.accountListing = accountListingService;
    }

    public void depositMoney(String clientID) {
        System.out.println("Type account ID");
        String accountID = withdrawDepositOperationCLIUI.requestClientAmountNumber();
        Account accountWithdraw = dbService.getClientAccountById(Integer.parseInt(accountID.charAt(accountID.length() - 1)+""));
        System.out.println("Type Amount of money");
        double amount = withdrawDepositOperationCLIUI.requestClientAmount();
        transactionDeposit.execute(accountWithdraw, amount);
    }
}
