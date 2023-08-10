package com.financies.financiesapi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financies.financiesapi.model.entities.TypeExpense;
import com.financies.financiesapi.model.entities.User;

public interface TypeExpenseRepository extends JpaRepository<TypeExpense, Integer> {

	Page<TypeExpense> findAllByUser(User user, Pageable pageable);

	List<TypeExpense> findAllByUser(User user);
	
	TypeExpense findByIdAndUser(Integer id, User user);

	boolean existsByDescriptionIgnoreCaseAndUser(String descrption, User user);

	boolean existsByIdAndUser(Integer id, User user);

}
