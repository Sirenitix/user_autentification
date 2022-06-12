package swag.rest.bank_app_delivery.service;

import org.springframework.stereotype.Repository;
import swag.rest.bank_app_delivery.entity.Account;

import java.util.List;

@Repository
public interface DBService {

    List<Account> getClientAccounts(int clientID);

    void createNewAccount(Account account);

    Account getClientAccountById(int id, int clientID);

    int getClientAccountMaxId();

    void updateAccount(Account account);

    void deleteClientAccountById(int id, int clientID);
}
