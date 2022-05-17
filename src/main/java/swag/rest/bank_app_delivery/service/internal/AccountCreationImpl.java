package swag.rest.bank_app_delivery.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.dao.AccountDAO;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.service.AccountCreationService;


@Service
public class AccountCreationImpl implements AccountCreationService {
    AccountDAO accountDAO;
    @Autowired
    DBService dbService;

    public AccountCreationImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void create(AccountType accountType, long bankID, String clientID, long accountID) {
        switch (accountType){
            case SAVING:
                dbService.createNewAccount(new SavingAccount(accountType, bankID, clientID, accountID, 0.0, true));
                break;
            case FIXED:
                dbService.createNewAccount(new FixedAccount(accountType, bankID, clientID, accountID, 0.0, false));
                break;
            case CHECKING:
                dbService.createNewAccount(new CheckingAccount(accountType, bankID, clientID, accountID, 0.0, true));
                break;
            default:
                System.out.println("Wrong input!");
        }
    }
}
