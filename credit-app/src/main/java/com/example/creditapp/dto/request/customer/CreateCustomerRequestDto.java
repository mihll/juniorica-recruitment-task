package com.example.creditapp.dto.request.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateCustomerRequestDto {
    Integer creditID;

    String customerFirstName;
    String customerLastName;
    String customerPESEL;
}
