package com.example.creditapplicationservice.model;

import java.math.BigDecimal;
import java.util.UUID;

public class CreditApplication {

    private UUID id;

    private BigDecimal amount;

    private int term;

    private BigDecimal income;

    private BigDecimal liabilities;

    private int creditScore;

    private Status status;

    public CreditApplication() {
    }

    public CreditApplication(UUID id, BigDecimal amount, int term, BigDecimal income, BigDecimal liabilities, int creditScore, Status status) {
        this.id = id;
        this.amount = amount;
        this.term = term;
        this.income = income;
        this.liabilities = liabilities;
        this.creditScore = creditScore;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        IN_PROGRESS,
        APPROVED,
        REJECTED
    }
}
