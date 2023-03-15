package com.financies.financiesapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financies.financiesapi.model.entities.Expense;
import com.financies.financiesapi.model.entities.User;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
	
	Page<Expense> findAllByUser(User user, Pageable pageable);

}
