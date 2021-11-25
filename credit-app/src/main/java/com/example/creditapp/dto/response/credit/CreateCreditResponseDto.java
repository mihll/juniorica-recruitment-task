package com.example.creditapp.dto.response.credit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class CreateCreditResponseDto {
    Integer creditID;
}
