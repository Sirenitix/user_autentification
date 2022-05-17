package swag.rest.bank_app_delivery.dao;

import org.springframework.jdbc.core.RowMapper;
import swag.rest.bank_app_delivery.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = null;
        String type = rs.getString("accountType");
        switch (type){
            case "SAVING":
                 account = new SavingAccount();
                 account.setAccountType(AccountType.SAVING);
                break;
            case "FIXED":
                 account = new FixedAccount();
                 account.setAccountType(AccountType.FIXED);
                 break;
            case "CHECKING":
                account = new CheckingAccount();
                account.setAccountType(AccountType.CHECKING);
                break;
        }
        account.setId(rs.getInt("id"));
        account.setClientID(rs.getString("clientID"));
        account.setBankID(Integer.parseInt(rs.getString("bankID")));
        account.setBalance(rs.getDouble("balance"));
        account.setWithdrawAllowed(rs.getBoolean("withdrawAllowed"));
        return account;
    }
}

