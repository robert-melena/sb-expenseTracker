package com.expensetracker.project.service;

import com.expensetracker.project.model.Expense;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ExpenseServiceImplementation  implements ExpenseService {

    private List<Expense> expenses = new ArrayList<>();
    private Long id = 1L;

    @Override
    public List<Expense> getAllExpenses(){
        return expenses;
    }

    @Override
    public void createExpense(Expense expense){
        expense.setExpenseId(id++);
        expense.setLocalDate();
        expenses.add(expense);
    }

    @Override
    public String deleteExpense(Long expenseId){
       Expense expense = expenses.stream()
               .filter(e -> e.getExpenseId().equals(expenseId))
               .findFirst()
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found!"));

       expenses.remove(expense);
       return "ExpenseID{" + expenseId+ "} deleted Successfully!";
    }

    @Override
    public Expense updateExpense(Expense expense, Long expenseId){
        Optional<Expense> expenseOptional =
                expenses.stream()
                        .filter(e -> e.getExpenseId().equals(expenseId))
                        .findFirst();

        if(expenseOptional.isPresent()){
            Expense existingExpense = expenseOptional.get();
            existingExpense.setLocalDate();
            existingExpense.setCategory(expense.getCategory());
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setPayment(expense.getPayment());
            return existingExpense;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Expense not found");
        }

    }

}
