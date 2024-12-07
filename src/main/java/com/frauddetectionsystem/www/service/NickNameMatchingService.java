package com.frauddetectionsystem.www.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
@author: prachi.shah
@date: December 6, 2024.
 */
public class NickNameMatchingService {

    private static final Map<String, String> FIRSTNAME_MAP_NICKNAME = new HashMap<>();

    // Mapping first name to known nicknames
    static {
        FIRSTNAME_MAP_NICKNAME.put("Cyril", "Cy");
        FIRSTNAME_MAP_NICKNAME.put("Jonathan", "John");
        FIRSTNAME_MAP_NICKNAME.put("Robert", "Bob");
        FIRSTNAME_MAP_NICKNAME.put("Elizabeth", "Liz");
        FIRSTNAME_MAP_NICKNAME.put("Alexander", "Alex");
        // ... add more nicknames here
    }

    // Checks if two names match using nicknames, substrings, or full name matching and return true if match found.
    public boolean isNicknameMatch(String firstName, String nickName) {
        if (firstName == null || firstName.isBlank() || nickName == null || nickName.isBlank()) {
            return false;
        }

        // Normalize names: lowercase, remove special characters and trim spaces
        String normalizedFirstName = normalizeName(firstName);
        String normalizedNickName = normalizeName(nickName);

        // Match for full first name or nickname match
        String firstNameMatch = FIRSTNAME_MAP_NICKNAME.getOrDefault(normalizedFirstName, normalizedFirstName);
        String nickNameMatch = FIRSTNAME_MAP_NICKNAME.getOrDefault(normalizedNickName, normalizedNickName);
        if (firstNameMatch.equalsIgnoreCase(nickNameMatch)) {
            return true;
        }

        // Substring match for names
        return normalizedFirstName.contains(normalizedNickName) || normalizedNickName.contains(normalizedFirstName);
    }

    // Splits and normalize a full name into parts. Removes special characters and convert to lowercase.
    public List<String> splitAndNormalizeFullName(String fullName) {
        return Arrays.stream(fullName.split(" ")).map(part -> part.replaceAll("[^a-zA-Z]", "").toLowerCase()).filter(part -> !part.isEmpty()).collect(Collectors.toList());
    }

    private String normalizeName(String name) {
        return name.trim().toLowerCase().replaceAll("[^a-z]", "");
    }
}
