package com.expensetracker.project.service;

import com.expensetracker.project.payload.ExpenseDTO;
import com.expensetracker.project.payload.ExpenseResponse;

public interface ExpenseService {

    ExpenseResponse getAllExpenses(Integer pageNumber, Integer pageSize);
    ExpenseDTO createExpense(ExpenseDTO expense);
    ExpenseDTO deleteExpense(Long expenseId);
    ExpenseDTO updateExpense(ExpenseDTO expense, Long expenseId);
}
