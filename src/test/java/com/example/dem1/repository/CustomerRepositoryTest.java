package com.example.dem1.repository;

import com.example.dem1.domain.Address;
import com.example.dem1.domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataMongoTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    private Address address;
    private Customer customer;
    @BeforeEach
    void setUp() {
        address = new Address("varanasi","UP","India","123456");
        customer = new Customer(101,"Ajay","a@gmail.com",address);
    }

    @AfterEach
    void tearDown() {
        address = null;
        customer = null;
        customerRepository.deleteAll();
    }

    @Test
    @DisplayName("Test case for saving customer object")
    void givenCustomerToSaveShouldReturnSavedCustomer() {
        customerRepository.save(customer);
        Customer customer1 = customerRepository.findById(customer.getCustomerId()).get();
        assertNotNull(customer1);
        assertEquals(customer.getCustomerId(), customer1.getCustomerId());
    }

    @Test
    @DisplayName("Test case for deleting customer")
    public void givenCustomerToDeleteShouldDeleteCustomer() {
        customerRepository.insert(customer);
        Customer customer1 = customerRepository.findById(customer.getCustomerId()).get();
        customerRepository.deleteById(customer1.getCustomerId());
        assertEquals(Optional.empty(), customerRepository.findById(customer.getCustomerId()));

    }

    @Test
    @DisplayName("Test case for getting all the  customer ")
    public void givenCustomerReturnAllCustomerDetails() {

        customerRepository.insert(customer);
        Address address1 = new Address("Raipur", "CG", "India","478544");
        Customer customer1 = new Customer(1002, "Harsh","h@gmail.com", address1);
        customerRepository.insert(customer1);

        List<Customer> list = customerRepository.findAll();
        assertEquals(2, list.size());
        assertEquals("Harsh", list.get(1).getCustomerName());

    }

}