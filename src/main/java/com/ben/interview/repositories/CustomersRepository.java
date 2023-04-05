package com.ben.interview.repositories;


import com.ben.interview.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customer,Integer> {
}
