package com.frauddetectionsystem.www.service;

import com.frauddetectionsystem.www.model.CustomerBankAccount;
import com.frauddetectionsystem.www.model.ThirdPartyBankAccount;
import com.frauddetectionsystem.www.model.ThirdPartyCustomer;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@author: prachi.shah
@date: December 6, 2024.
 */
public class MatchDetectionService {

    private static final LevenshteinDistance LEVENSHTEIN_DISTANCE = new LevenshteinDistance();
    private final NickNameMatchingService nickNameMatchingService;

    public MatchDetectionService(NickNameMatchingService nickNameMatchingService) {
        this.nickNameMatchingService = nickNameMatchingService;
    }

    // Matches a list of customer bank accounts to a list of third-party bank accounts.
    public Map<Integer, String> matchAccounts(List<CustomerBankAccount> customerBankAccounts, List<ThirdPartyBankAccount> thirdPartyBankAccounts) {
        Map<Integer, String> accounts = new HashMap<>();
        int matches = 0, mismatches = 0;

        for (ThirdPartyBankAccount thirdPartyBankAccount : thirdPartyBankAccounts) {
            CustomerBankAccount customerBankAccount = findCustomerById(customerBankAccounts, thirdPartyBankAccount.getSanFranciscoCreditUnionCompanyId());
            String MATCH = "Match";
            String MISMATCH = "Mismatch";

            if (customerBankAccount != null) {
                int matchCount = match(customerBankAccount, thirdPartyBankAccount);
                boolean isMatch = matchCount >= 2;
                accounts.put(thirdPartyBankAccount.getLinkId(), isMatch ? MATCH : MISMATCH);
                if (isMatch) {
                    matches++;
                } else {
                    mismatches++;
                }
            } else {
                accounts.put(thirdPartyBankAccount.getLinkId(), MISMATCH);
                mismatches++;
            }
        }

        System.out.printf("Valid Accounts Approved for ACH Transaction between San Francisco Credit Union and Plaid Third-Party Bank Account: %d%n", matches);
        System.out.printf("Invalid Accounts Disapproved for ACH Transaction between San Francisco Credit Union and Plaid Third-Party Bank Account: %d%n", mismatches);
        return accounts;
    }

    // Matches a customer bank account to a third-party bank account.
    private int match(CustomerBankAccount customerBankAccount, ThirdPartyBankAccount thirdPartyBankAccount) {
        int matchCount = 0;
        matchCount += matchNames(customerBankAccount, thirdPartyBankAccount);
        matchCount += matchEmailAddresses(customerBankAccount, thirdPartyBankAccount);
        matchCount += matchPhoneNumbers(customerBankAccount, thirdPartyBankAccount);
        return matchCount;
    }

    // Match names between a customer bank account and a third-party bank account.
    private int matchNames(CustomerBankAccount customerBankAccount, ThirdPartyBankAccount thirdPartyBankAccount) {
        for (String fullName : thirdPartyBankAccount.getNames()) {
            List<String> fullNameParts = nickNameMatchingService.splitAndNormalizeFullName(fullName);

            // Check if any part of the name matches customer bank account record
            for (String part : fullNameParts) {
                for (ThirdPartyCustomer thirdPartyCustomer : customerBankAccount.getUsers()) {
                    // Match full first name or nickname
                    if (nickNameMatchingService.isNicknameMatch(thirdPartyCustomer.getFirstName(), part)) {
                        return 1;   // Match found
                    }
                    // Full name match
                    if (LEVENSHTEIN_DISTANCE.apply(thirdPartyCustomer.getFirstName(), part) <= 1 || LEVENSHTEIN_DISTANCE.apply(thirdPartyCustomer.getLastName(), part) <= 1) {
                        return 1;   // Match found
                    }
                }
                // Match trade name or legal name
                if (LEVENSHTEIN_DISTANCE.apply(customerBankAccount.getTradeName(), part) <= 2 || LEVENSHTEIN_DISTANCE.apply(customerBankAccount.getLegalName(), part) <= 2) {
                    return 1;   // Match found
                }
            }
        }
        return 0;   // No match found
    }

    // Match email addresses.
    private int matchEmailAddresses(CustomerBankAccount customerBankAccount, ThirdPartyBankAccount thirdPartyBankAccount) {
        for (String emailAddress : thirdPartyBankAccount.getEmails()) {
            if (emailAddress.equalsIgnoreCase(customerBankAccount.getContactEmail())) {
                return 1;   // Match found
            }
            for (ThirdPartyCustomer thirdPartyCustomer : customerBankAccount.getUsers()) {
                if (emailAddress.equalsIgnoreCase(thirdPartyCustomer.getEmail())) {
                    return 1;   // Match found
                }
            }
        }
        return 0;   // No match found
    }

    // Match phone numbers.
    private int matchPhoneNumbers(CustomerBankAccount customerBankAccount, ThirdPartyBankAccount thirdPartyBankAccount) {
        for (String phoneNumber : thirdPartyBankAccount.getPhoneNumbers()) {
            if (phoneNumber.replaceAll("\\D", "").equals(customerBankAccount.getContactPhoneNumber())) {
                return 1;   // Match found
            }
        }
        return 0;   // No match found
    }

    // Finds a customer by their San Francisco Credit Union Company ID.
    private CustomerBankAccount findCustomerById(List<CustomerBankAccount> customerBankAccounts, int companyId) {
        return customerBankAccounts.stream().filter(customerBankAccount -> customerBankAccount.getSanFranciscoCreditUnionCompanyId() == companyId)
                .findFirst()
                .orElse(null);
    }
}
