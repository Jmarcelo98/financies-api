package com.financies.financiesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financies.financiesapi.model.entities.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

}
