package com.expensetracker.project.controller;

import com.expensetracker.project.config.AppConstants;
import com.expensetracker.project.payload.ExpenseDTO;
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

//    LEAVE OFF HERE ADD MORE QUERY PARAMS AND implement APIRESPONSE for CustomExceptions

    @GetMapping()
    public ResponseEntity<ExpenseResponse> getAllExpenses(@RequestParam(name = "pageNumber",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                          @RequestParam(name = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer pageSize){
        ExpenseResponse expenseResponse = expenseService.getAllExpenses(pageNumber,pageSize);
        return new ResponseEntity<>(expenseResponse, HttpStatus.OK); //response body that is requested AND http status code
    }

    @PostMapping()
    public ResponseEntity<ExpenseDTO> createExpense(@Valid @RequestBody ExpenseDTO expenseDTO){ //Deserialization... converting Request body String to Object
        ExpenseDTO savedExpenseDTO = expenseService.createExpense(expenseDTO);
        return new ResponseEntity<>(savedExpenseDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<ExpenseDTO> deleteExpense(@PathVariable Long expenseId){
             ExpenseDTO deletedExpenseDTO = expenseService.deleteExpense(expenseId);
             return new ResponseEntity<>(deletedExpenseDTO,HttpStatus.OK);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<String> updateExpense(@PathVariable Long expenseId,
                                                @RequestBody ExpenseDTO expenseDTO){
            ExpenseDTO savedExpense = expenseService.updateExpense(expenseDTO,expenseId);
            return new ResponseEntity<>("Expense with expenseId{" + expenseId +"} has been updated!",HttpStatus.OK);

    }
}
