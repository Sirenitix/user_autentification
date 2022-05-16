package swag.rest.bank_app_delivery.service.internal;

import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.dao.AccountDAO;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.service.AccountDepositService;

import static swag.rest.bank_app_delivery.entity.AccountType.CHECKING;

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
        Account updatedAccount;
        switch (account.getAccountType()){
            case SAVING:
                updatedAccount = new SavingAccount(AccountType.SAVING,accountToUpdate.getId(),accountToUpdate.getClientID(),accountToUpdate.getBankID(),balance + amount, accountToUpdate.isWithdrawAllowed());
                break;
            case CHECKING:
                updatedAccount = new CheckingAccount(AccountType.CHECKING,accountToUpdate.getId(),accountToUpdate.getClientID(),accountToUpdate.getBankID(),balance + amount, accountToUpdate.isWithdrawAllowed());
                break;
            case FIXED:
                updatedAccount = new CheckingAccount(AccountType.FIXED,accountToUpdate.getId(),accountToUpdate.getClientID(),accountToUpdate.getBankID(),balance + amount, accountToUpdate.isWithdrawAllowed());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + account.getAccountType());
        }
        accountDAO.updateAccount(accountToUpdate, updatedAccount);
        System.out.println("" + amount + "$ transferred to " + String.format("%03d%06d", 1, account.getBankID()));
    }
}
