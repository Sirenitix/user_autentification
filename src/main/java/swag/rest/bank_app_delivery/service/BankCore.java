package swag.rest.bank_app_delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.entity.AccountType;
import swag.rest.bank_app_delivery.service.DBService;

@Service
public class BankCore {
    static long id = 1;
    long lastAccountNumber = 1;
    AccountCreationService accountCreation;
    @Qualifier("DBServiceImpl")
    @Autowired
    DBService dbService;

    public BankCore(AccountCreationService accountCreationService) {
        this.accountCreation = accountCreationService;
    }

    public void createNewAccount(AccountType accountType, String clientID){
        long lastIndex = 0;
        if(dbService.getClientAccountMaxId() != 0){
            lastIndex = dbService.getClientAccountMaxId() + 1;
        }else {
            lastIndex = lastAccountNumber;
        }
        accountCreation.create(accountType, id, clientID, lastIndex);
        this.incrementLastAccountNumber();
    }

    void incrementLastAccountNumber(){
        lastAccountNumber++;
    }


}
