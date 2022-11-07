package com.example.dem1.service;

import com.example.dem1.domain.Customer;
import com.example.dem1.exception.CustomerNotfoundException;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> gatAllCustomerData() throws Exception;
    boolean deleteCustomer(int customerId) throws CustomerNotfoundException;
    List<Customer> getAllCustomersByCity(String city) throws CustomerNotfoundException;

}