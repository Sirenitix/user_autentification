package swag.rest.bank_app_delivery.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.dao.AccountDAO;
import swag.rest.bank_app_delivery.dao.TransactionDAO;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.service.AccountWithdrawService;
import swag.rest.bank_app_delivery.service.DBService;

@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService {

    AccountDAO accountDAO;

    @Qualifier("DBServiceImpl")
    @Autowired
    DBService dbService;

    @Autowired
    TransactionDAO transactionDAO;

    public AccountWithdrawServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void withdraw(double amount, AccountWithdraw accountWithdraw) {
        double balance =  dbService.getClientAccountById((int) accountWithdraw.getBankID()).getBalance();
        if (balance >= amount) {
            accountWithdraw.setBalance(balance - amount);
            dbService.updateAccount(accountWithdraw);
            String transaction = "" + amount + "$ transferred from " + String.format("%03d%06d", 1, accountWithdraw.getBankID());
            transactionDAO.addTransaction(new Transaction(String.format("%03d%06d", 1, accountWithdraw.getBankID()),transaction,balance-amount));
            System.out.println(transaction);
        }else {
            System.out.println("Not enough money");
        }
    }
}
