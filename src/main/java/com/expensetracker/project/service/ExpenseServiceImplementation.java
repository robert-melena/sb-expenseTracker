package com.expensetracker.project.service;

import com.expensetracker.project.exceptions.APIException;
import com.expensetracker.project.exceptions.ResourceNotFoundException;
import com.expensetracker.project.model.Expense;
import com.expensetracker.project.payload.ExpenseDTO;
import com.expensetracker.project.payload.ExpenseResponse;
import com.expensetracker.project.repository.ExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseServiceImplementation  implements ExpenseService {

//    Modify expenseService to be more efficent

    private final ExpenseRepository expenseRepository;

    private ModelMapper modelMapper;
    public ExpenseServiceImplementation(ExpenseRepository expenseRepository, ModelMapper modelMapper){
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
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
                        expense.getExpenseId(),
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
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO){
        Expense expense  = modelMapper.map(expenseDTO,Expense.class);
        //setting the date of expense here
        expense.setLocalDate();
        Expense savedExpense = expenseRepository.save(expense);
        //to map expense to an ExpenseDTO object
        return modelMapper.map(savedExpense,ExpenseDTO.class);
    }

    @Override
    public ExpenseDTO deleteExpense(Long expenseId){
        Expense deletedExpense = expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException("Expense","Expense ID", expenseId));
        expenseRepository.delete(deletedExpense);
        return modelMapper.map(deletedExpense,ExpenseDTO.class);
    }

    @Override
    public ExpenseDTO updateExpense(ExpenseDTO expenseDTO, Long expenseId){
        //keep to throw the exception, will help tell us it exists
        Expense existingExpense = expenseRepository
                .findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense","Expense ID",expenseId));

        //to ensure null values dont overwrite anything
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        //copy values from ExpenseDTO to existing Expense (overwrites them)
        modelMapper.map(expenseDTO,existingExpense);
        //save to database
        Expense updatedExpense = expenseRepository.save(existingExpense);
        //return ExpenseDTO
        return modelMapper.map(updatedExpense,ExpenseDTO.class);
    }

}
