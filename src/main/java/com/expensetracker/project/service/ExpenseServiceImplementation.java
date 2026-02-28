package com.expensetracker.project.service;

import com.expensetracker.project.exceptions.APIException;
import com.expensetracker.project.exceptions.ResourceNotFoundException;
import com.expensetracker.project.model.Expense;
import com.expensetracker.project.payload.ExpenseDTO;
import com.expensetracker.project.payload.ExpenseResponse;
import com.expensetracker.project.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ExpenseResponse getAllExpenses(Integer pageNumber, Integer pageSize){


        //getting a pageable with number of pages and size per page
        Pageable pageDetails = PageRequest.of(pageNumber,pageSize);
        //Returns a Page of type Expense meeting the paging restriction provided in the Pageable
        Page<Expense> expensePage = expenseRepository.findAll(pageDetails);
        List<Expense> expenses = expensePage.getContent();
        if(expenses.isEmpty()){
            throw new APIException("No expenses at this time");
        }

        ///Mapping list of Expenses to list of ExpenseDTO
        List<ExpenseDTO> expenseDTOS = expenses.stream()
                .map( expense -> new ExpenseDTO(
                        expense.getCategory(),
                        expense.getAmount(),
                        expense.getPayment(),
                        expense.getLocalDate()
                        )).toList();

        //Creating an ExponseResponse object and setting the content
        ExpenseResponse expenseResponse = new ExpenseResponse();
        expenseResponse.setContent(expenseDTOS);
        //setting meta data
        expenseResponse.setPageNumber(expensePage.getNumber());
        expenseResponse.setPageSize(expensePage.getSize());
        expenseResponse.setTotalElements(expensePage.getTotalElements());
        expenseResponse.setTotalPages(expensePage.getTotalPages());
        expenseResponse.setLastPage(expensePage.isLast());
        return expenseResponse;
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
