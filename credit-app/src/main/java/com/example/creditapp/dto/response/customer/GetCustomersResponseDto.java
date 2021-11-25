package com.example.creditapp.dto.response.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class GetCustomersResponseDto {
    List<CustomerResponseDto> customers;
}
