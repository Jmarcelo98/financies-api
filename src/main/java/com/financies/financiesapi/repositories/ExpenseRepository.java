package com.financies.financiesapi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.financies.financiesapi.model.entities.Expense;
import com.financies.financiesapi.model.entities.User;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
	
	Page<Expense> findAllByUser(User user, Pageable pageable);
	
	
	@Query("SELECT SUM(e.value), te.description FROM Expense e " + "JOIN e.user u " + "JOIN e.typeExpense te " +
			"WHERE u.id = :user " +
			"and " + 
			"e.isReceived = true " + 
			"and " +
			"year(e.dateReference) = :yearReference " +
			"and " + 
			"month(e.dateReference) = :monthReference GROUP BY te.description" )
	List<String> findAllByYearMonth(@Param("user") Integer user,
			@Param("yearReference") Integer yearReference, @Param("monthReference") Integer monthReference);

}
