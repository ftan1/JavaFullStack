package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service  // Marks this class as a service that Spring can manage
public class RewardService {
    private final TransactionRepository transactionRepository;
    private final TransactionRepositoryCustom transactionRepositoryCustom;

    // Constructor-based injection
    @Autowired
    public RewardService(TransactionRepository transactionRepository, TransactionRepositoryCustom transactionRepositoryCustom) {
        this.transactionRepository = transactionRepository;
        this.transactionRepositoryCustom = transactionRepositoryCustom;
    }

    // Method to get all unique customers
    public List<Long> getAllCustomers() {
        return transactionRepositoryCustom.findDistinctCustomerIds();
    }

    // Method to calculate points based on the transaction amount
    public int calculatePoints(Double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int) ((amount - 100) * 2);  // 2 points for each dollar over 100
            amount = 100.0;
        }
        if (amount > 50) {
            points += (int) (amount - 50);  // 1 point for each dollar between 50 and 100
        }
        return points;
    }

    // Fetch transactions for a specific customer and month, and calculate the points
    public int getMonthlyRewards(Long customerId, int month, int year) {
        // Define the start and end date of the month
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        // Fetch transactions for the given customer and date range
        List<Transaction> transactions = transactionRepository.findAllByCustomerIdAndDateBetween(customerId, start, end);

        // Calculate and return total points
        return transactions.stream().mapToInt(t -> calculatePoints(t.getAmount())).sum();
    }

    // Fetch all transactions for a customer and calculate total points
    public int getTotalRewards(Long customerId) {
        List<Transaction> transactions = transactionRepository.findAllByCustomerId(customerId);
        return transactions.stream().mapToInt(t -> calculatePoints(t.getAmount())).sum();
    }
}
