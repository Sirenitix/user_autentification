package swag.rest.bank_app_delivery.dao;

import org.springframework.stereotype.Repository;
import swag.rest.bank_app_delivery.entity.Transaction;

import java.util.List;

@Repository
public interface TransactionDAO {
    List<Transaction> getTransactions();
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactionsById(String id);
}
