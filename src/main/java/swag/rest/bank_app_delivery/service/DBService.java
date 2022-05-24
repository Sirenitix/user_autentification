package swag.rest.bank_app_delivery.service;

import org.springframework.stereotype.Repository;
import swag.rest.bank_app_delivery.entity.Account;

import java.util.List;

@Repository
public interface DBService {

    List<Account> getClientAccounts();

    void createNewAccount(Account account);

    Account getClientAccountById(long id);

    int getClientAccountMaxId();

    void updateAccount(Account account);
}
