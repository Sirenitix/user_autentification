package swag.rest.bank_app_delivery.dao.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import swag.rest.bank_app_delivery.dao.TransactionDAO;
import swag.rest.bank_app_delivery.dao.TransactionMapper;
import swag.rest.bank_app_delivery.entity.Transaction;

import java.util.List;

@Repository
public class MemoryTransactionDAO implements TransactionDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public MemoryTransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transaction> getTransactions() {
        final String SQL_GET_ALL = "select * from transaction";
        return jdbcTemplate.query(SQL_GET_ALL, new TransactionMapper());
    }

    @Override
    public List<Transaction> getTransactionsById(String id) {
        final String SQL_FIND_ACCOUNT = "select * from transaction where accountID = ?";
        return jdbcTemplate.query(SQL_FIND_ACCOUNT,new TransactionMapper(),new Object[] { id });
    }

    @Override
    public void addTransaction(Transaction transaction) {
        final String SQL_INSERT_ACCOUNT = "insert into transaction(accountID,transaction,currentBalance) values(?,?,?)";
        jdbcTemplate.update(SQL_INSERT_ACCOUNT, transaction.getAccountID(),transaction.getTransaction(),transaction.getCurrentBalance());
    }
}
