package swag.rest.bank_app_delivery.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.dao.AccountDAO;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.service.AccountCreationService;
import swag.rest.bank_app_delivery.service.DBService;


@Service
public class AccountCreationImpl implements AccountCreationService {
    AccountDAO accountDAO;
    @Qualifier("DBServiceImpl")
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
                System.out.println("Bank account created");
                break;
            case FIXED:
                dbService.createNewAccount(new FixedAccount(accountType, bankID, clientID, accountID, 0.0, false));
                System.out.println("Bank account created");
                break;
            case CHECKING:
                dbService.createNewAccount(new CheckingAccount(accountType, bankID, clientID, accountID, 0.0, true));
                System.out.println("Bank account created");
                break;
            default:
                System.out.println("Wrong input!");
        }
    }
}
