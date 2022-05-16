package swag.rest.bank_app_delivery.service.internal;

import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.dao.AccountDAO;
import swag.rest.bank_app_delivery.entity.Account;
import swag.rest.bank_app_delivery.entity.AccountWithdraw;
import swag.rest.bank_app_delivery.service.AccountWithdrawService;

@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService {

    AccountDAO accountDAO;

    public AccountWithdrawServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void withdraw(double amount, AccountWithdraw accountWithdraw) {
       double balance =  accountDAO.getClientWithdrawAccount("1",String.valueOf(accountWithdraw.getBankID())).getBalance();
        if (balance >= amount) {
            AccountWithdraw accountToUpdate = accountDAO.getClientWithdrawAccount("1",String.valueOf(accountWithdraw.getBankID()));
            accountToUpdate.setBalance(balance - amount);
            accountDAO.updateAccount(accountWithdraw,accountToUpdate);
            System.out.println("" + amount + "$ transferred from " + String.format("%03d%06d", 1, accountWithdraw.getBankID()));
        }else {
            System.out.println("Not enough money");
        }
    }
}
