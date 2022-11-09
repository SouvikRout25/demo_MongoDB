package com.example.dem1.service;

import com.example.dem1.domain.Address;
import com.example.dem1.domain.Customer;
import com.example.dem1.exception.CustomerNotfoundException;
import com.example.dem1.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceimpl;

    private Customer customer1,customer2;
    List<Customer> customerList;
    Address address1,address2;


    @BeforeEach
    void setUp() {
        address1 = new Address("varanasi","UP","India","123456");
        address2 = new Address("Raipur","CG","India","458744");
        customer1 = new Customer(101,"Ajay","a@gmail.com",address1);
        customer2 = new Customer(102,"Harsh","h@gmail.com",address2);
        customerList = Arrays.asList(customer1,customer2);
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    public void saveData() throws Exception{
        //when(customerRepository.findById(customer1.getCustomerId())).thenReturn(Optional.ofNullable(null));
        when(customerRepository.save(any())).thenReturn(customer1);
        assertEquals(customer1,customerServiceimpl.saveCustomer(customer1));
        verify(customerRepository,times(1)).save(any());
//       verify(customerRepository,times(1)).findById(any());

    }
//    @Test
//    public void givenCustomerToSaveReturnCustomerFailure(){
//        when(customerRepository.findById(customer1.getCustomerId())).thenReturn(Optional.ofNullable(customer1));
//        assertThrows(CustomerAlreadyExistsException.class,()->customerService.saveCustomerDetail(customer1));
//        verify(customerRepository,times(0)).save(any());
//        verify(customerRepository,times(1)).findById(any());
//    }

    @Test
    public void givenCustomerToDeleteShouldDeleteSuccess() throws CustomerNotfoundException {
        when(customerRepository.findById(customer1.getCustomerId())).thenReturn(Optional.ofNullable(customer1));
        boolean flag = customerServiceimpl.deleteCustomer(customer1.getCustomerId());
        assertEquals(true,flag);

        verify(customerRepository,times(1)).deleteById(any());
        verify(customerRepository,times(1)).findById(any());
    }

}