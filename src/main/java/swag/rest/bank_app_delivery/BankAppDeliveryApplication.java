package swag.rest.bank_app_delivery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import swag.rest.bank_app_delivery.entity.AccountType;
import swag.rest.bank_app_delivery.entity.internal.AccountBasicCLI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import swag.rest.bank_app_delivery.entity.internal.TransactionDepositCLI;
import swag.rest.bank_app_delivery.entity.internal.TransactionWithdrawCLI;
import swag.rest.bank_app_delivery.service.MyCLI;
import swag.rest.bank_app_delivery.service.internal.DBService;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories("swag.rest.bank_app_delivery")
public class BankAppDeliveryApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    @Autowired
    DBService dbService;

    public static void main(String[] args) {
        SpringApplication.run(BankAppDeliveryApplication.class);
    }

    @Override
    public void run(String... arg0){
            //dbService.insertData();
//            System.out.println(dbService.getClientAccounts());
//            System.out.println(dbService.getClientAccountById(2));
            String  helpMessage  =
                    "1 - show accounts\n" +
                    "2 - create account\n" +
                    "3 - deposit\n" +
                    "4 - withdraw\n" +
                    "5 - transfer\n" +
                    "6 - this message\n" +
                    "7 - exit";
            String clientID = "1";
            System.out.printf("Welcome to CLI bank service\n");
            System.out.printf("Enter operation number: \n");
            System.out.println(helpMessage);
            while (true) {
                try {
                AccountBasicCLI accountBasicCLI = context.getBean(AccountBasicCLI.class);
                MyCLI myCLI = context.getBean(MyCLI.class);
                TransactionDepositCLI transactionDepositCLI = context.getBean(TransactionDepositCLI.class);
                TransactionWithdrawCLI transactionWithdrawCLI = context.getBean(TransactionWithdrawCLI.class);
                switch (myCLI.getScanner().nextLine()) {
                    case "1":
                        accountBasicCLI.getAccounts(clientID);
                        break;
                    case "2":
                        accountBasicCLI.createAccountRequest(clientID);
                        break;
                    case "3":
                        transactionDepositCLI.depositMoney(clientID);
                        break;
                    case "4":
                        transactionWithdrawCLI.withdrawMoney(clientID);
                        break;
                    case "6":
                        System.out.println(helpMessage);
                        break;
                    case "7":
                        System.out.println("Application closed");
                        System.exit(0);
                    default:
                        System.out.println("Wrong input!");
                        break;
                }
            }
                catch (ClassCastException c){
                    if (c.getMessage().contains("FixedAccount cannot be cast")) {
                        System.out.println("FixedAccount cannot be cast!" + c.getMessage());
                    }
                }
                catch (Exception e){
                    System.out.println("Wrong input! " + e.getMessage());
                }

        }

    }
}

