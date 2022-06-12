package swag.rest.bank_app_delivery.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import swag.rest.bank_app_delivery.dao.TransactionDAO;
import swag.rest.bank_app_delivery.entity.*;
import swag.rest.bank_app_delivery.jwt.JwtUtil;
import swag.rest.bank_app_delivery.service.BankCore;
import swag.rest.bank_app_delivery.service.DBService;
import swag.rest.bank_app_delivery.service.UserService;
import swag.rest.bank_app_delivery.service.internal.CustomUserDetailsService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


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
    @Autowired
    AuthenticationManager authenticationManager;

    JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        User userEntity = userService.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(userEntity.getUsername()).toUriString());
        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/accounts")
    public List<Account> getAccounts(){
        String auth = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return dbService.getClientAccounts(userService.findByUsername(auth).get().getId());
    }

    @PostMapping("/accounts")
    public String createAccount(@RequestParam("account_type") String account_type){
        String auth = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(auth);
        bankCore.createNewAccount(AccountType.valueOf(account_type),String.valueOf(userService.findByUsername(auth).get().getId()));
        return "New account created";
    }


    @DeleteMapping("/accounts/{account_id}")
    public String deleteAccount(@PathVariable("account_id")String account_id){
        String auth = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dbService.deleteClientAccountById(Integer.parseInt(account_id) - 1000000, userService.findByUsername(auth).get().getId());
        return "Account "+ account_id + " deleted";
    }


    @GetMapping("/accounts/{account_id}")
    public Account getAccount(@PathVariable("account_id")String account_id){
        String auth = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return dbService.getClientAccountById(Integer.parseInt(account_id) - 1000000, userService.findByUsername(auth).get().getId());
    }


    @GetMapping("/accounts/{account_id}/transactions")
    public List<Transaction> getAccounts(@PathVariable("account_id")String account_id){
        String auth = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return transactionDAO.getTransactionsById(account_id, userService.findByUsername(auth).get().getId());
    }


    @PostMapping("/accounts/{account_id}/withdraw")
    public String withdrawAccount(@PathVariable("account_id")String account_id, @RequestParam("amount") double amount){
        String auth = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        transactionWithdraw.execute((AccountWithdraw) dbService.getClientAccountById(Integer.parseInt(account_id) - 1000000, userService.findByUsername(auth).get().getId()),amount, userService.findByUsername(auth).get().getId());
        return "" + amount + "$ transferred to " + account_id;
    }


    @PostMapping("/accounts/{account_id}/deposit")
    public String depositAccount(@PathVariable("account_id")String account_id, @RequestParam("amount") double amount){
        String auth = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        transactionDeposit.execute(dbService.getClientAccountById(Integer.parseInt(account_id) - 1000000, userService.findByUsername(auth).get().getId()),amount, userService.findByUsername(auth).get().getId());
        return  "" + amount + "$ transferred from " + account_id;
    }

    @PostMapping("/accounts/{account_id}/transfer")
    public String transferAccount(@PathVariable("account_id")String account_id, @RequestParam("amount") double amount, @RequestBody Destination_account destination_account) throws Exception {
        String auth = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        double balance =  dbService.getClientAccountById( Integer.parseInt(account_id) - 1000000, userService.findByUsername(auth).get().getId()).getBalance();
        System.out.println(destination_account.getDestination_account_id() + " destination_account");
        if(balance >= amount && dbService.getClientAccountById( Integer.parseInt(account_id) - 1000000, userService.findByUsername(auth).get().getId()).isWithdrawAllowed()){
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
        return  "" + amount + "$ transferred from " + account_id + " to " + destination_account.getDestination_account_id();
    }

    @Operation(description = "Login")
    @PostMapping("/login")
    public void fakeLogin(@RequestBody User user) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        user.setRole("ROLE_USER");
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),user.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.createAccessToken(user.getUsername(), request.getRequestURL().toString(),
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        Cookie cookie = new Cookie("token", jwt);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok(jwt);
    }

    @Operation(description = "Logout")
    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }



}
