package com.expensetracker.project.repository;


import com.expensetracker.project.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//To indicate this is a Data Access Object (DAO)
@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}
