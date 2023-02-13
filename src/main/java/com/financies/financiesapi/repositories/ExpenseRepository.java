package com.financies.financiesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financies.financiesapi.model.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
