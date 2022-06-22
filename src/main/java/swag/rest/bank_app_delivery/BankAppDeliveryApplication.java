package swag.rest.bank_app_delivery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableJpaRepositories("swag.rest.bank_app_delivery")
public class BankAppDeliveryApplication  {

    public static void main(String[] args) {
        SpringApplication.run(BankAppDeliveryApplication.class);
    }

}

