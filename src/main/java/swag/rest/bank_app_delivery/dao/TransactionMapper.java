package swag.rest.bank_app_delivery.dao;

import org.springframework.jdbc.core.RowMapper;
import swag.rest.bank_app_delivery.entity.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction(rs.getString("transaction"));
        return transaction;
    }
}
