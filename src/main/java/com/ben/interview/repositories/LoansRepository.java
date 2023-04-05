package com.ben.interview.repositories;

import com.ben.interview.models.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoansRepository extends CrudRepository<Loan,Integer> {
}
