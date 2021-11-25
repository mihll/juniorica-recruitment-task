package com.example.customerapp.service;

import com.example.customerapp.dto.request.customer.CreateCustomerRequestDto;
import com.example.customerapp.dto.request.customer.GetCustomersRequestDto;
import com.example.customerapp.model.Customer;
import com.example.customerapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(CreateCustomerRequestDto createCustomerRequestDto) {
        Customer customerToCreate = new Customer();
        customerToCreate.setCreditID(createCustomerRequestDto.getCreditID());
        customerToCreate.setCustomerFirstName(createCustomerRequestDto.getCustomerFirstName());
        customerToCreate.setCustomerLastName(createCustomerRequestDto.getCustomerLastName());
        customerToCreate.setCustomerPESEL(createCustomerRequestDto.getCustomerPESEL());

        return customerRepository.save(customerToCreate);
    }

    @Override
    public List<Customer> getAllForCreditIDs(List<Integer> creditIDs) {
        return customerRepository.findAllById(creditIDs);
    }
}
