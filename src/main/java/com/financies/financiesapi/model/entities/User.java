package com.financies.financiesapi.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "USER_INFO")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String cpf;

	private LocalDate dateCreation;

	private LocalDate dateBirth;

	@Lob
	private Byte[] photo;

	@OneToMany(mappedBy = "user", orphanRemoval = false)
	private List<Income> incomes;

	@OneToMany(mappedBy = "user", orphanRemoval = false)
	private List<Expense> expenses;

	@OneToMany(mappedBy = "user", orphanRemoval = false)
	private List<TypeIncome> typeIncomes;

	@OneToMany(mappedBy = "user", orphanRemoval = false)
	private List<TypeExpense> typeExpenses;

}
