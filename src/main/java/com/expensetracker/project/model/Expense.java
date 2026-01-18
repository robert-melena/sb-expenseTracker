package com.expensetracker.project.model;

import java.time.LocalDate;

public class Expense {
    private Long expenseId;
    private LocalDate date;
    private Category category;
    private double amount;
    private Payment payment;

    public Expense(Long expenseId, Category category, double amount, Payment payment,LocalDate date) {
        this.expenseId = expenseId;
        this.category = category;
        this.amount = amount;
        this.payment = payment;
        this.date = date;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public LocalDate getLocalDate() {
        return date;
    }

    public void setLocalDate() {
        this.date =  LocalDate.now();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
