package com.financies.financiesapi.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financies.financiesapi.model.entities.TypeIncome;
import com.financies.financiesapi.model.entities.User;

public interface TypeIncomeRepository extends JpaRepository<TypeIncome, Integer> {

	List<TypeIncome> findAllByUser(User user, Pageable pageable);
	
	boolean existsByDescriptionIgnoreCaseAndUser(String description, User user);
	
	boolean existsByIdAndUser(Integer id, User user);

}
