package com.example.creditapp.dto.request.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateProductRequestDto {
    Integer creditID;

    String productName;
    Integer productValue;
}
