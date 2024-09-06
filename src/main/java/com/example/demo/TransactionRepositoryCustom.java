package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    // Query to get distinct customer IDs
    public List<Long> findDistinctCustomerIds() {
        return mongoTemplate.query(Transaction.class)
                .distinct("customerId")
                .as(Long.class)
                .all();
    }
}
