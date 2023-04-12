package com.financies.financiesapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.financies.financiesapi.model.entities.Expense;
import com.financies.financiesapi.model.entities.Income;
import com.financies.financiesapi.model.entities.User;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
//	
//	Page<Expense> findAllByUser(User user, Pageable pageable);
//	
//	
//	@Query("SELECT SUM(e.value), te.description FROM Expense e " + "JOIN e.user u " + "JOIN e.typeExpense te " +
//			"WHERE u.id = :user " +
//			"and " + 
//			"e.isReceived = true " + 
//			"and " +
//			"year(e.dateReference) = :yearReference " +
//			"and " + 
//			"month(e.dateReference) = :monthReference GROUP BY te.description" )
//	List<String> findAllByYearMonth(@Param("user") Integer user,
//			@Param("yearReference") Integer yearReference, @Param("monthReference") Integer monthReference);
	
	@Query("SELECT i FROM Expense i " + "JOIN i.typeExpense ti " + "JOIN i.user u " + "WHERE u.id = :user " + "and "
			+ "(:isReceived is null or i.isReceived = :isReceived) " + "and "
			+ "(:yearReference is null or year(i.dateReference) = :yearReference) " + "and "
			+ "(:monthReference is null or month(i.dateReference) = :monthReference) " + "and "
			+ "(:typeExpense is null or ti.id = :typeExpense) " + "ORDER BY i.dateReference DESC")
	Page<Expense> findAllByFilterAndPageable(@Param("user") Integer user, @Param("isReceived") Boolean isReceived,
			@Param("yearReference") Integer yearReference, @Param("monthReference") Integer monthReference,
			@Param("typeExpense") Integer typeExpense, Pageable pageable);

	Expense findByIdAndUser(Integer id, User user);

	@Query("SELECT SUM(i.value) FROM Expense i " + "JOIN i.user u " + "WHERE u.id = :user " + "and "
			+ "i.isReceived = TRUE ")
	Optional<Double> getCurrentExpense(@Param("user") Integer user);

	boolean existsByIdAndUser(Integer id, User user);

}
