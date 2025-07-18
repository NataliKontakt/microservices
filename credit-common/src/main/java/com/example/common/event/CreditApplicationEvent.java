package com.example.common.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public class CreditApplicationEvent {
    private UUID applicationId;
    private BigDecimal amount;
    private int term;
    private BigDecimal income;
    private BigDecimal liabilities;
    private int creditScore;

//    public CreditApplicationEvent() {
//    }

     @JsonCreator
     public CreditApplicationEvent(
         @JsonProperty("applicationId") UUID applicationId,
         @JsonProperty("amount") BigDecimal amount,
         @JsonProperty("term") int term,
         @JsonProperty("income") BigDecimal income,
         @JsonProperty("liabilities") BigDecimal liabilities,
         @JsonProperty("creditScore") int creditScore
     ) {
         this.applicationId = applicationId;
         this.amount = amount;
         this.term = term;
         this.income = income;
         this.liabilities = liabilities;
         this.creditScore = creditScore;
     }


    public UUID getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(UUID applicationId) {
        this.applicationId = applicationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(BigDecimal liabilities) {
        this.liabilities = liabilities;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}
