package com.example.productapp.dto.request.product;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateProductRequestDto {
    Integer creditID;

    String productName;
    Integer productValue;
}
