package com.acme.recommendationservice.repository;

import com.acme.recommendationservice.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>
{
}
