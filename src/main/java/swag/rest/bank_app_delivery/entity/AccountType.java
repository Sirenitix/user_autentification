package swag.rest.bank_app_delivery.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public enum AccountType {
    CHECKING("CHECKING"),
    SAVING("SAVING"),
    FIXED("FIXED");

    private String type;

    public String getType() {
        return type;
    }

    AccountType(String type) {
        this.type = type;
    }
}
