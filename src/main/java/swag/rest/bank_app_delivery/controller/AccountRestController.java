package swag.rest.bank_app_delivery.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import swag.rest.bank_app_delivery.dao.TransactionDAO;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.entity.internal.TransactionDepositCLI;
import swag.rest.bank_app_delivery.entity.internal.TransactionWithdrawCLI;
import swag.rest.bank_app_delivery.service.BankCore;
import swag.rest.bank_app_delivery.service.DBService;
import swag.rest.bank_app_delivery.service.UserService;

import java.net.URI;
import java.util.List;


@RestController("/")
public class AccountRestController  {
    @Qualifier("DBServiceImpl")
    @Autowired
    DBService dbService;
    @Autowired
    TransactionDAO transactionDAO;
    @Autowired
    BankCore bankCore;
    @Autowired
    TransactionDeposit transactionDeposit;
    @Autowired
    TransactionWithdraw transactionWithdraw;
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        User userEntity = userService.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(userEntity.getUsername()).toUriString());
        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/accounts")
    public List<Account> getProducts(){
        return dbService.getClientAccounts();
    }

    @PostMapping("/accounts")
    public String createProduct(@RequestParam("account_type") String account_type){
        bankCore.createNewAccount(AccountType.valueOf(account_type),"1");
        return "New account created";
    }


    @DeleteMapping("/accounts/{account_id}")
    public String deleteProduct(@PathVariable("account_id")String account_id){
        dbService.deleteClientAccountById(Integer.parseInt(account_id) - 1000000);
        return "Account "+ account_id + " deleted";
    }


    @GetMapping("/accounts/{account_id}")
    public Account getProduct(@PathVariable("account_id")String account_id){
        return dbService.getClientAccountById(Integer.parseInt(account_id) - 1000000);
    }


    @GetMapping("/accounts/{account_id}/transactions")
    public List<Transaction> getProducts(@PathVariable("account_id")String account_id){
        return transactionDAO.getTransactionsById(account_id);
    }


    @PostMapping("/accounts/{account_id}/withdraw")
    public String withdrawProduct(@PathVariable("account_id")String account_id, @RequestParam("amount") double amount){
        transactionWithdraw.execute((AccountWithdraw) dbService.getClientAccountById(Integer.parseInt(account_id) - 1000000),amount);
        return "" + amount + "$ transferred to " + account_id;
    }


    @PostMapping("/accounts/{account_id}/deposit")
    public String depositProduct(@PathVariable("account_id")String account_id, @RequestParam("amount") double amount){
        transactionDeposit.execute(dbService.getClientAccountById(Integer.parseInt(account_id) - 1000000),amount);
        return  "" + amount + "$ transferred from " + account_id;
    }

    @Operation(description = "Login")
    @PostMapping("/login")
    public void fakeLogin(@RequestBody User user) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @Operation(description = "Logout")
    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

}
