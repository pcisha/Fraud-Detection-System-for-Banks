package com.frauddetectionsystem.www.fraudDetection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frauddetectionsystem.www.model.CustomerBankAccount;
import com.frauddetectionsystem.www.model.ThirdPartyBankAccount;
import com.frauddetectionsystem.www.service.MatchDetectionService;
import com.frauddetectionsystem.www.service.NickNameMatchingService;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/*
@author: prachi.shah
@date: December 6, 2024.
 */
public class FraudDetectionApplication {

    public static void main(String[] args) throws Exception {
        System.out.println("Started Fraud Detection Service at: " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        NickNameMatchingService nickNameMatchingService = new NickNameMatchingService();
        MatchDetectionService matchDetectionService = new MatchDetectionService(nickNameMatchingService);

        // Get the San Francisco Credit Union Bank customer account information
        List<CustomerBankAccount> customerBankAccounts = mapper.readValue(new File("src/main/resources/san-francisco-credit-union-customers.json"), new TypeReference<List<CustomerBankAccount>>() {
        });

        // Get the Plaid Third-Party Bank customer account information
        List<ThirdPartyBankAccount> thirdPartyBankAccounts = mapper.readValue(new File("src/main/resources/plaid-third-party-banks.json"), new TypeReference<List<ThirdPartyBankAccount>>() {
        });

        // Fraud detection
        Map<Integer, String> fraudDetectionResults = matchDetectionService.matchAccounts(customerBankAccounts, thirdPartyBankAccounts);

        // Account matching and fraud detection results
        fraudDetectionResults.forEach((linkId, isMatch) -> {
            String accountStatus = isMatch.equals("Match") ? "Valid Account" : "Invalid Account";
            System.out.printf("Bank ID: %d - %s%n", linkId, accountStatus);
        });
    }
}
