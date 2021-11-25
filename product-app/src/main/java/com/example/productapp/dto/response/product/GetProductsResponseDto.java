package com.example.productapp.dto.response.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class GetProductsResponseDto {
    List<ProductResponseDto> products;
}
