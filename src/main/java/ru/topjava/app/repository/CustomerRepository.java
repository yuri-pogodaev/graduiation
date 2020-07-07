package ru.topjava.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.topjava.app.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}