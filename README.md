# Fraud Detection System for Third-Party Bank Transactions (ACH)

## Overview

This project implements a fraud detection system for ACH (Automated Clearing House) transactions between financial
institutions, such as San Francisco Credit Union and Plaid third-party bank accounts. The system validates customer
accounts and determines if they are legitimate based on various criteria such as names, emails, phone numbers, and
nicknames. The algorithm uses fuzzy matching techniques (Levenshtein Distance) and nickname mappings to handle
real-world data inconsistencies.

## Features

### Data Matching:

- Compares customer records with third-party bank accounts.
- Matches names, emails, and phone numbers with flexibility for minor variations.

### Fuzzy Name Matching:

- Handles typos and alternative spellings using Levenshtein Distance.
- Incorporates nickname matching for names like "Cy" → "Cyril."

### Flexible Matching Criteria:

- Requires at least two matching fields (e.g., name, email, phone) to validate an account.

### Output:

- Saves fraud detection results to an output file for further analysis.
- Results: src/main/resources/fraud-detection-report.txt

## Technology Stack

### Programming Language:

- Java (version 8 or higher).

### Libraries:

- Jackson for JSON file parsing, writing and entity object mapping.
- Apache Commons Text for Levenshtein Distance, and string matching and similarity.

### Build Tool:

- Maven

## Configuration

The following files are required for input data:

- san-francisco-credit-union-customers.json: Contains customer bank account records for San Francisco Credit Union.
- plaid-third-party-banks.json: Contains Plaid third-party bank account records.

## Customization

- Adjust Matching Criteria: Modify thresholds for Levenshtein Distance or the number of required matches.
- Nickname Mapping: Add more nicknames and customizations for nickname matches.
- Data Sources: Replace input files with APIs or database queries for dynamic data handling.

## Future Enhancements

- Scalability: Use parallel processing for large datasets. Integrate with distributed systems like Apache Spark for
  batch processing.
- Enhanced Matching: Add geolocation or account history for fraud detection. Include configurable weights for different
  matching criteria.
- Real-Time Detection: Extend to handle real-time ACH transactions using Kafka or RabbitMQ.
- Metadata: Use metadata for validity of accounts.

#### Author: Prachi Shah

#### Date: December 6, 2024
