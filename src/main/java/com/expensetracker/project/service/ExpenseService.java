package com.expensetracker.project.service;

import com.expensetracker.project.model.Expense;
import com.expensetracker.project.payload.ExpenseResponse;

public interface ExpenseService {

    ExpenseResponse getAllExpenses();
    void createExpense(Expense expense);
    String deleteExpense(Long expenseId);
    Expense updateExpense(Expense expense, Long expenseId);
}
