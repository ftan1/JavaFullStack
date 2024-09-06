package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")  // Base URL for all reward-related endpoints
public class RewardController {

    private final RewardService rewardService;

    @Autowired
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    /*// Endpoint to get monthly reward points for a customer
    @GetMapping("/{customerId}/monthly")
    public ResponseEntity<Map<String, Integer>> getMonthlyRewards(
            @PathVariable Long customerId,
            @RequestParam int month,
            @RequestParam int year) {
        int points = rewardService.getMonthlyRewards(customerId, month, year);
        Map<String, Integer> response = new HashMap<>();
        response.put("points", points);
        return ResponseEntity.ok(response);
    }*/

    // Endpoint to get total reward points for a customer
    @GetMapping("/{customerId}/total")
    public ResponseEntity<Map<String, Integer>> getTotalRewards(@PathVariable Long customerId) {
        int points = rewardService.getTotalRewards(customerId);
        Map<String, Integer> response = new HashMap<>();
        response.put("points", points);
        return ResponseEntity.ok(response);
    }

    // Endpoint to get all unique customers
    @GetMapping("/customers")
    public List<Long> getAllCustomers() {
        return rewardService.getAllCustomers();
    }
}
