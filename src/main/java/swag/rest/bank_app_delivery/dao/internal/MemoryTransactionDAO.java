package swag.rest.bank_app_delivery.dao.internal;

import org.springframework.stereotype.Repository;
import swag.rest.bank_app_delivery.dao.TransactionDAO;
import swag.rest.bank_app_delivery.entity.Transaction;

import java.util.List;

@Repository
public class MemoryTransactionDAO implements TransactionDAO {
    List<Transaction> transactions;

    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

    @Override
    public void addTransaction(Transaction transaction) {

    }
}
