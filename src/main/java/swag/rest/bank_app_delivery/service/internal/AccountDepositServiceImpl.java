package swag.rest.bank_app_delivery.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.dao.AccountDAO;
import swag.rest.bank_app_delivery.dao.TransactionDAO;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.service.AccountDepositService;
import swag.rest.bank_app_delivery.service.DBService;

@Service
public class AccountDepositServiceImpl implements AccountDepositService {
    AccountDAO accountDAO;
    @Qualifier("DBServiceImpl")
    @Autowired
    DBService dbService;

    @Autowired
    TransactionDAO transactionDAO;

    public AccountDepositServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void deposit(double amount, Account account) {
        double balance =  dbService.getClientAccountById((int) account.getBankID()).getBalance();
        account.setBalance(balance + amount);
        dbService.updateAccount(account);
        String transaction = "" + amount + "$ transferred to " + String.format("%03d%06d", 1, account.getBankID());
        transactionDAO.addTransaction(new Transaction(String.format("%03d%06d", 1, account.getBankID()),transaction,balance+amount));
        System.out.println(transaction);
    }
}
