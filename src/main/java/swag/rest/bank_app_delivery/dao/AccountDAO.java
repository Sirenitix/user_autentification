package swag.rest.bank_app_delivery.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import swag.rest.bank_app_delivery.entity.*;


import java.util.List;

@Repository
public interface AccountDAO {
    List<Account> getClientAccounts(String clientID);
    void createNewAccount(Account account);
    void updateAccount(Account accountWithdraw,Account account);
    List<Account> getClientAccountsByType(String clientID, AccountType accountType);
    AccountWithdraw getClientWithdrawAccount(String clientID, String accountID);
    Account getClientAccount(String clientID, String accountID);

}
