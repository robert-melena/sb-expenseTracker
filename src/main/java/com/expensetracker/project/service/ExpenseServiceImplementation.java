package com.expensetracker.project.service;

import com.expensetracker.project.exceptions.ResourceNotFoundException;
import com.expensetracker.project.model.Expense;
import com.expensetracker.project.repository.ExpenseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseServiceImplementation  implements ExpenseService {

//    Modify expenseService to be more efficent

    private final ExpenseRepository expenseRepository;
    public ExpenseServiceImplementation(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }


    @Override
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    @Override
    public void createExpense(Expense expense){
        expense.setLocalDate();
        expenseRepository.save(expense);
    }

    @Override
    public String deleteExpense(Long expenseId){
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException("Expense","Expense ID", expenseId));
        expenseRepository.delete(expense);
        return "Expense with expense ID: " + expenseId + " deleted successfully";
    }

    @Override
    public Expense updateExpense(Expense expense, Long expenseId){
        Expense savedExpense = expenseRepository
                .findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense","Expense ID",expenseId));
        savedExpense.setExpenseId(expenseId);
        return savedExpense;

    }

}
