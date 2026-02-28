package com.expensetracker.project.payload;


import com.expensetracker.project.model.Category;
import com.expensetracker.project.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//exposing only the fields we want to api
//notice not showing data and id here
//note: right now just showing all same info as normal, could what we dont want to show

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private Long expenseId;
    private Category category;
    private double amount;
    private Payment payment;
    private LocalDate localDate;
}
