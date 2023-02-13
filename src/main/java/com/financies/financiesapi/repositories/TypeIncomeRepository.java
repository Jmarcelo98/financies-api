package com.financies.financiesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financies.financiesapi.model.entities.TypeIncome;

public interface TypeIncomeRepository extends JpaRepository<TypeIncome, Integer> {

}
