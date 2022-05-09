package swag.rest.bank_app_delivery.entity.internal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import swag.rest.bank_app_delivery.entity.TransactionDeposit;
import swag.rest.bank_app_delivery.entity.WithdrawDepositOperationCLIUI;
import swag.rest.bank_app_delivery.service.AccountListingService;

@Component
public class TransactionDepositCLI {
    TransactionDeposit transactionDeposit;
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    AccountListingService accountListing;

    public TransactionDepositCLI(TransactionDeposit transactionDeposit, WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI, AccountListingService accountListingService) {
        this.transactionDeposit = transactionDeposit;
        this.withdrawDepositOperationCLIUI = withdrawDepositOperationCLIUI;
        this.accountListing = accountListingService;
    }


    public void depositMoney(String clientID) {
        accountListing.getClientAccount(clientID,withdrawDepositOperationCLIUI.requestClientAmountNumber()) ;

    }
}
