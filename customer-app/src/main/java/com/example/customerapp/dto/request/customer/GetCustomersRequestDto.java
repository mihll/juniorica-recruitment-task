package com.example.customerapp.dto.request.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class GetCustomersRequestDto {
    List<Integer> creditIDs;
}
