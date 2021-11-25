package com.example.creditapp.dto.request.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class GetProductsRequestDto {
    List<Integer> creditIDs;
}
