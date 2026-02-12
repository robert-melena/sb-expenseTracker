package com.expensetracker.project.controller;

import com.expensetracker.project.model.Expense;
import com.expensetracker.project.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @GetMapping()
    public ResponseEntity<List<Expense>> getAllExpenses(){
        List<Expense> expenses = expenseService.getAllExpenses();
        return new ResponseEntity<>(expenses, HttpStatus.OK); //response body that is requested AND http status code
    }

    @PostMapping()
    public ResponseEntity<String> createExpense(@Valid @RequestBody Expense expense){ //Deserialization... converting Request body String to Object
        expenseService.createExpense(expense);
        return new ResponseEntity<>("Expense Added Successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId){
         try{
             String status = expenseService.deleteExpense(expenseId);
             return new ResponseEntity<>(status,HttpStatus.OK);
         }catch (ResponseStatusException e){
             return new ResponseEntity<>(e.getReason(),e.getStatusCode());
         }
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<String> updateExpense(@PathVariable Long expenseId,
                                                @RequestBody Expense expense){
        try{
            Expense savedExpense = expenseService.updateExpense(expense,expenseId);
            return new ResponseEntity<>("Expense with expenseId{" + expenseId +"} has been updated!",HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }

    }
}
