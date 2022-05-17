package swag.rest.bank_app_delivery.dao.internal;

import org.springframework.stereotype.Repository;
import swag.rest.bank_app_delivery.dao.AccountDAO;
import swag.rest.bank_app_delivery.entity.Account;
import swag.rest.bank_app_delivery.entity.AccountType;
import swag.rest.bank_app_delivery.entity.AccountWithdraw;


import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryAccountDAO implements AccountDAO {

    public List<Account> accountList = new ArrayList<>();

    @Override
    public List<Account> getClientAccounts(String clientID) {
        return this.accountList;
    }

    @Override
    public void createNewAccount(Account account) {
        accountList.add(account);
        System.out.println("Bank account created");
    }

    @Override
    public void updateAccount(Account accountToUpdate,Account updatedAccount) {
        this.accountList.set(accountList.indexOf(accountToUpdate), updatedAccount);
    }

    @Override
    public List<Account> getClientAccountsByType(String clientID, AccountType accountType) {
        return null;
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        return (AccountWithdraw) accountList.stream().filter(acc -> acc.getBankID() == Integer.parseInt(accountID.substring(accountID.length() - 1))).findFirst().get();
    }

    @Override
    public Account getClientAccount(String clientID, String accountID) {
        return accountList.stream().filter(acc -> acc.getBankID() == Integer.parseInt(accountID.substring(accountID.length() - 1))).findFirst().get();
    }


}
