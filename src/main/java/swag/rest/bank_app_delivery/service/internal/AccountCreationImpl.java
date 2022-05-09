package swag.rest.bank_app_delivery.service.internal;

import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.dao.AccountDAO;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.service.AccountCreationService;


@Service
public class AccountCreationImpl implements AccountCreationService {
    AccountDAO accountDAO;

    public AccountCreationImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void create(AccountType accountType, long bankID, String clientID, long accountID) {
        switch (accountType){
            case SAVING:
                accountDAO.createNewAccount(new SavingAccount(accountType, bankID, clientID, accountID, 0.0, true));
                break;
            case FIXED:
                accountDAO.createNewAccount(new FixedAccount(accountType, bankID, clientID, accountID, 0.0, false));
                break;
            case CHECKING:
                accountDAO.createNewAccount(new CheckingAccount(accountType, bankID, clientID, accountID, 0.0, true));
                break;
            default:
                System.out.println("Wrong input!");
        }
    }
}
