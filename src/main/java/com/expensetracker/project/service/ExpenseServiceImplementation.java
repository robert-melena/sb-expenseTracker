package com.expensetracker.project.service;

import com.expensetracker.project.model.Expense;
import com.expensetracker.project.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
       Expense expense = expenseRepository.findAll().stream()
               .filter(e -> e.getExpenseId().equals(expenseId))
               .findFirst()
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found!"));

       expenseRepository.delete(expense);
       return "ExpenseID{" + expenseId+ "} deleted Successfully!";
    }

    @Override
    public Expense updateExpense(Expense expense, Long expenseId){
        Optional<Expense> expenseOptional =
                expenseRepository.findAll().stream()
                        .filter(e -> e.getExpenseId().equals(expenseId))
                        .findFirst();

        if(expenseOptional.isPresent()){
            Expense existingExpense = expenseOptional.get();
            expense.setExpenseId(expenseId);
            return existingExpense;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Expense not found");
        }

    }

}
