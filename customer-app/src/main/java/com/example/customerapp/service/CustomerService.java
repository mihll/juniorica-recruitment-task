package com.example.customerapp.service;

import com.example.customerapp.dto.request.customer.CreateCustomerRequestDto;
import com.example.customerapp.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(CreateCustomerRequestDto createCustomerRequestDto);
    List<Customer> getAllForCreditIDs(List<Integer> creditIDs);
}
