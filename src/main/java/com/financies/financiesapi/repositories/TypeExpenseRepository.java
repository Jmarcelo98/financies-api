package com.financies.financiesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financies.financiesapi.model.entities.TypeExpense;

public interface TypeExpenseRepository extends JpaRepository<TypeExpense, Integer> {

}
