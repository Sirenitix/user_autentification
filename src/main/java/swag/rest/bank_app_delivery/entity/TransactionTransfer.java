package swag.rest.bank_app_delivery.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.service.DBService;
import swag.rest.bank_app_delivery.service.UserService;

@Service
public class TransactionTransfer {

    @Qualifier("DBServiceImpl")
    @Autowired
    DBService dbService;

    @Autowired
    TransactionDeposit transactionDeposit;

    @Autowired
    TransactionWithdraw transactionWithdraw;

    @Autowired
    UserService userService;


    public void execute(double amount, Destination_account destination_account, String account_id, String auth) throws Exception {
        double balance =  dbService.getClientAccountById( Integer.parseInt(account_id) - 1000000, userService
                .findByUsername(auth).get().getId()).getBalance();
            System.out.println(destination_account.getDestination_account_id() + " destination_account");
            if(balance >= amount
                    && dbService.getClientAccountById( Integer.parseInt(account_id) - 1000000, userService.findByUsername(auth).get().getId()).isWithdrawAllowed()
                    && dbService.getClientAccountByClientId(Integer.parseInt(destination_account.getDestination_account_id())-1000000).isPresent()){
            transactionWithdraw.execute((AccountWithdraw) dbService.getClientAccountById(Integer.parseInt(account_id) - 1000000, userService.findByUsername(auth).get().getId()),amount, userService.findByUsername(auth).get().getId());
            transactionDeposit.execute(
                    dbService.getClientAccountById(Integer.parseInt(destination_account.getDestination_account_id()) - 1000000, userService.findById(Integer.parseInt(destination_account.getDestination_account_id()) - 1000000).get().getId()),
                    amount,
                    userService.findById(Integer.parseInt(destination_account.getDestination_account_id()) - 1000000).get().getId());
        }else if(!dbService.getClientAccountById( Integer.parseInt(account_id) - 1000000, userService.findByUsername(auth).get().getId()).isWithdrawAllowed()){
            throw new Exception("You can't transfer from FIXED account");
        }else {
            throw new Exception("Not enough money");
        }
    }
}
