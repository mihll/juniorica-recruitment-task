package com.example.creditapp.dto.response.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomerResponseDto {
    Integer creditID;

    String customerFirstName;
    String customerLastName;
    String customerPESEL;
}
