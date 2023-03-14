package com.financies.financiesapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.financies.financiesapi.model.entities.Income;
import com.financies.financiesapi.model.entities.User;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

	@Query("SELECT i FROM Income i " 
			+ "JOIN i.typeIncome ti " 
			+ "JOIN i.user u " 
			+ "WHERE u.id = :user " 
			+ "and "
			+ "(:isReceived is null or i.isReceived = :isReceived) " 
			+ "and "
			+ "(:yearReference is null or year(i.dateReference) = :yearReference) " 
			+ "and "
			+ "(:monthReference is null or month(i.dateReference) = :monthReference) " 
			+ "and "
			+ "(:typeIncome is null or ti.id = :typeIncome) ")
	Page<Income> findAllByFilterAndPageable(@Param("user") Integer user, @Param("isReceived") Boolean isReceived,
			@Param("yearReference") Integer yearReference, @Param("monthReference") Integer monthReference,
			@Param("typeIncome") Integer typeIncome, Pageable pageable);

	Page<Income> findAllByUserOrderByDateInclusionDesc(User user, Pageable pageable);

	Income findByIdAndUser(Integer id, User user);

	boolean existsByIdAndUser(Integer id, User user);

}
