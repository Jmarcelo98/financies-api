package com.financies.financiesapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financies.financiesapi.model.entities.Income;
import com.financies.financiesapi.model.entities.User;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

	Page<Income> findAllByUserOrderByDateInclusionDesc(User user, Pageable pageable);

	Income findByIdAndUser(Integer id, User user);
	
	boolean existsByIdAndUser(Integer id, User user);

}
