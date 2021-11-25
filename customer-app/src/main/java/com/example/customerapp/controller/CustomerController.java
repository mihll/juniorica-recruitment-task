package com.example.customerapp.controller;

import com.example.customerapp.dto.request.customer.CreateCustomerRequestDto;
import com.example.customerapp.dto.request.customer.GetCustomersRequestDto;
import com.example.customerapp.dto.response.customer.CustomerResponseDto;
import com.example.customerapp.dto.response.customer.GetCustomersResponseDto;
import com.example.customerapp.model.Customer;
import com.example.customerapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CreateCustomerRequestDto createCustomerRequestDto) {
        customerService.createCustomer(createCustomerRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/getAllForCreditIDs")
    public ResponseEntity<GetCustomersResponseDto> getCustomers(@RequestBody @Valid GetCustomersRequestDto getCustomersRequestDto) {
        List<Customer> customers = customerService.getAllForCreditIDs(getCustomersRequestDto.getCreditIDs());
        GetCustomersResponseDto responseDto = new GetCustomersResponseDto();
        responseDto.setCustomers(customers
                .stream()
                .map(customer -> {
                    CustomerResponseDto customerResponseDto = new CustomerResponseDto();
                    customerResponseDto.setCreditID(customer.getCreditID());
                    customerResponseDto.setCustomerFirstName(customer.getCustomerFirstName());
                    customerResponseDto.setCustomerLastName(customer.getCustomerLastName());
                    customerResponseDto.setCustomerPESEL(customer.getCustomerPESEL());
                    return customerResponseDto;
                })
                .collect(Collectors.toList())
        );
        return ResponseEntity.ok(responseDto);
    }
}
