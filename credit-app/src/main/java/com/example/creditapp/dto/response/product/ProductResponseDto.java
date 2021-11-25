package com.example.creditapp.dto.response.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductResponseDto {
    Integer creditID;

    String productName;
    Integer productValue;
}
