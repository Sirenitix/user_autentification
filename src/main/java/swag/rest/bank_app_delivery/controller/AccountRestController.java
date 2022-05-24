package swag.rest.bank_app_delivery.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import swag.rest.bank_app_delivery.entity.Account;
import swag.rest.bank_app_delivery.service.DBService;

import java.util.List;

@Api
@RestController("/")
public class AccountRestController  {

    @Autowired
    DBService dbService;


    @GetMapping("/accounts")
    public List<Account> getProducts(){
        return dbService.getClientAccounts();
    }

}
