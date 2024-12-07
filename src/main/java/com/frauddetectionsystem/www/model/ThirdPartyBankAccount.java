package com.frauddetectionsystem.www.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*
@author: prachi.shah
@date: December 6, 2024.
 */
@JsonIgnoreProperties
public class ThirdPartyBankAccount {

    @JsonProperty
    private int sanFranciscoCreditUnionCompanyId;

    @JsonProperty
    private int linkId;

    @JsonProperty
    private String bank;

    @JsonProperty
    private List<String> names;

    @JsonProperty
    private List<String> emails;

    @JsonProperty
    private List<String> phoneNumbers;

    @JsonProperty
    private String fraudComments;

    public int getSanFranciscoCreditUnionCompanyId() {
        return sanFranciscoCreditUnionCompanyId;
    }

    public void setSanFranciscoCreditUnionCompanyId(int sanFranciscoCreditUnionCompanyId) {
        this.sanFranciscoCreditUnionCompanyId = sanFranciscoCreditUnionCompanyId;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getFraudComments() {
        return fraudComments;
    }

    public void setFraudComments(String fraudComments) {
        this.fraudComments = fraudComments;
    }
}
