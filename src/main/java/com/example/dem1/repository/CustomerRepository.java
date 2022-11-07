package com.example.dem1.repository;

import com.example.dem1.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends MongoRepository<Customer,Integer> {


    @Query("{'customerAddress.city': {$in:[?0]}}")
    List<Customer> findAllCustomersFromCity(String city);
}