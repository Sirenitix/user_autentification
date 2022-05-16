package swag.rest.bank_app_delivery.service.internal;

import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.dao.AccountDAO;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.service.AccountWithdrawService;

import static swag.rest.bank_app_delivery.entity.AccountType.CHECKING;

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
            AccountWithdraw updatedAccount;
            switch (accountWithdraw.getAccountType()){
                case SAVING:
                    updatedAccount = new SavingAccount(AccountType.SAVING,accountToUpdate.getId(),accountToUpdate.getClientID(),accountToUpdate.getBankID(),balance - amount, accountToUpdate.isWithdrawAllowed());
                    break;
                case CHECKING:
                    updatedAccount = new CheckingAccount(AccountType.CHECKING,accountToUpdate.getId(),accountToUpdate.getClientID(),accountToUpdate.getBankID(),balance - amount, accountToUpdate.isWithdrawAllowed());
                    break;
                case FIXED:
                    throw new ClassCastException("FixedAccount cannot be cast");
                default:
                    throw new IllegalStateException("Unexpected value: " + accountWithdraw.getAccountType());
            }
            accountDAO.updateAccount(accountToUpdate,updatedAccount);
            System.out.println("" + amount + "$ transferred from " + String.format("%03d%06d", 1, accountWithdraw.getBankID()));
        }else {
            System.out.println("Not enough money");
        }
    }
}
