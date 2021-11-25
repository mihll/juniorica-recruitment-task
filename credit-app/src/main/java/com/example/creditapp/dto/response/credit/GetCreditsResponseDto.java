package com.example.creditapp.dto.response.credit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetCreditsResponseDto {
    List<CreditResponseDto> allCredits;
}
