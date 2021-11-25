package com.example.creditapp.dto.response.credit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CreditResponseDto {
    String creditName;

    String clientFirstName;
    String clientLastName;
    String clientPESEL;

    String productName;
    Integer productValue;
}
