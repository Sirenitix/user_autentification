package swag.rest.bank_app_delivery.service.internal;

import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.dao.AccountDAO;
import swag.rest.bank_app_delivery.entity.Account;
import swag.rest.bank_app_delivery.entity.AccountWithdraw;
import swag.rest.bank_app_delivery.service.AccountDepositService;

@Service
public class AccountDepositServiceImpl implements AccountDepositService {
    AccountDAO accountDAO;

    public AccountDepositServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void deposit(double amount, Account account) {
        double balance =  accountDAO.getClientAccount("1",String.valueOf(account.getBankID())).getBalance();
        Account accountToUpdate = accountDAO.getClientAccount("1",String.valueOf(account.getBankID()));
        accountToUpdate.setBalance(balance + amount);
        accountDAO.updateAccount(account, accountToUpdate);
        System.out.println("" + amount + "$ transferred to " + String.format("%03d%06d", 1, account.getBankID()));
    }
}
