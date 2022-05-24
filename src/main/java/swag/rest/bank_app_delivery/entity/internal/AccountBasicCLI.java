package swag.rest.bank_app_delivery.entity.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.service.BankCore;
import swag.rest.bank_app_delivery.service.internal.AccountListingServiceImpl;
import swag.rest.bank_app_delivery.service.DBService;

@Component
public class AccountBasicCLI {
    CreateAccountOperationUI createAccountOperationUI;
    BankCore bankCore;
    AccountListingServiceImpl accountListing;
    @Qualifier("DBServiceImpl")
    @Autowired
    DBService dbService;



    public AccountBasicCLI(CreateAccountOperationUI createAccountOperationUI, BankCore bankCore, AccountListingServiceImpl accountListing) {
        this.createAccountOperationUI = createAccountOperationUI;
        this.bankCore = bankCore;
        this.accountListing = accountListing;
    }

    public void getAccounts(String clientID){
        System.out.println(dbService.getClientAccounts());
    }

    public void createAccountRequest(String clientID){
        bankCore.createNewAccount(createAccountOperationUI.requestAccountType(), clientID);
    }

}
