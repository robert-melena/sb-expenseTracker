package com.expensetracker.project.payload;


import lombok.Data;

import java.util.List;

//will contain a List of type ExpenseDTO
@Data
public class ExpenseResponse {
    private List<ExpenseDTO> content;

}
