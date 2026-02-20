package com.expensetracker.project.controller;

import com.expensetracker.project.model.Expense;
import com.expensetracker.project.payload.ExpenseResponse;
import com.expensetracker.project.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @GetMapping()
    public ResponseEntity<ExpenseResponse> getAllExpenses(){
        ExpenseResponse expenseResponse = expenseService.getAllExpenses();
        return new ResponseEntity<>(expenseResponse, HttpStatus.OK); //response body that is requested AND http status code
    }

    @PostMapping()
    public ResponseEntity<String> createExpense(@Valid @RequestBody Expense expense){ //Deserialization... converting Request body String to Object
        expenseService.createExpense(expense);
        return new ResponseEntity<>("Expense Added Successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId){
             String status = expenseService.deleteExpense(expenseId);
             return new ResponseEntity<>(status,HttpStatus.OK);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<String> updateExpense(@PathVariable Long expenseId,
                                                @RequestBody Expense expense){
            Expense savedExpense = expenseService.updateExpense(expense,expenseId);
            return new ResponseEntity<>("Expense with expenseId{" + expenseId +"} has been updated!",HttpStatus.OK);

    }
}
