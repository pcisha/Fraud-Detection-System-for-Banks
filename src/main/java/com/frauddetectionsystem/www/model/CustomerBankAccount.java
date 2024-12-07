package com.frauddetectionsystem.www.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*
@author: prachi.shah
@date: December 6, 2024.
 */
@JsonIgnoreProperties
public class CustomerBankAccount {

    @JsonProperty
    private int sanFranciscoCreditUnionCompanyId;

    @JsonProperty
    private List<ThirdPartyCustomer> thirdPartyCustomers;

    @JsonProperty
    private String tradeName;

    @JsonProperty
    private String legalName;

    @JsonProperty
    private String contactEmail;

    @JsonProperty
    private String contactPhoneNumber;

    public int getSanFranciscoCreditUnionCompanyId() {
        return sanFranciscoCreditUnionCompanyId;
    }

    public void setSanFranciscoCreditUnionCompanyId(int sanFranciscoCreditUnionCompanyId) {
        this.sanFranciscoCreditUnionCompanyId = sanFranciscoCreditUnionCompanyId;
    }

    public List<ThirdPartyCustomer> getUsers() {
        return thirdPartyCustomers;
    }

    public void setUsers(List<ThirdPartyCustomer> thirdPartyCustomers) {
        this.thirdPartyCustomers = thirdPartyCustomers;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }
}
