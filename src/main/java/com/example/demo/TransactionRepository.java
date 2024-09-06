package com.example.demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    // Find all transactions for a customer within a specific date range
    List<Transaction> findAllByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);

    // Find all transactions for a specific customer
    List<Transaction> findAllByCustomerId(Long customerId);

    // Find distinct customer IDs from the transactions collection
    List<Long> findDistinctCustomerIdBy();
}
