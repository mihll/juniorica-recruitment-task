package com.example.creditapp.dto.request.credit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCreditRequestDto {
    String creditName;

    String customerFirstName;
    String customerLastName;
    String customerPESEL;

    String productName;
    Integer productValue;
}
