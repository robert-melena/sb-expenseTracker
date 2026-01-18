package com.expensetracker.project.service;

import com.expensetracker.project.model.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getAllExpenses();
    void createExpense(Expense expense);
    String deleteExpense(Long expenseId);
    Expense updateExpense(Expense expense, Long expenseId);
}
