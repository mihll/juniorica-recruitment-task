package com.example.customerapp.dto.request.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCustomerRequestDto {
    Integer creditID;

    String customerFirstName;
    String customerLastName;
    String customerPESEL;
}
