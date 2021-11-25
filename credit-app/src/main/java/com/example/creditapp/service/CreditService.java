package com.example.creditapp.service;

import com.example.creditapp.dto.request.credit.CreateCreditRequestDto;
import com.example.creditapp.dto.response.credit.CreditResponseDto;
import com.example.creditapp.model.Credit;

import java.util.List;

public interface CreditService {
    Credit createCredit(CreateCreditRequestDto createCreditRequestDto);
    List<CreditResponseDto> getCredits();
}
