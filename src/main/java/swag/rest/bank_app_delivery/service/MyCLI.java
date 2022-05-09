package swag.rest.bank_app_delivery.service;

import org.springframework.stereotype.Service;
import swag.rest.bank_app_delivery.entity.*;

import java.util.Scanner;

@Service
public class MyCLI implements CLIUI {

    private Scanner scanner;

    public MyCLI() {
        this.scanner = new Scanner(System.in);;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public double requestClientAmount(){
        return 0;
    }

    public String requestClientAmountNumber() {
        return scanner.nextLine();
    }


    @Override
    public AccountType requestAccountType() {
        return AccountType.valueOf(scanner.nextLine());
    }
}
