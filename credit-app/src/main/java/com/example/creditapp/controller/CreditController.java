package com.example.creditapp.controller;

import com.example.creditapp.dto.request.credit.CreateCreditRequestDto;
import com.example.creditapp.dto.response.credit.CreateCreditResponseDto;
import com.example.creditapp.dto.response.credit.CreditResponseDto;
import com.example.creditapp.dto.response.credit.GetCreditsResponseDto;
import com.example.creditapp.model.Credit;
import com.example.creditapp.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    CreditService creditService;

    @PostMapping("/create")
    public ResponseEntity<CreateCreditResponseDto> createCredit(@RequestBody @Valid CreateCreditRequestDto createCreditRequestDto) {
        Credit createdCredit = creditService.createCredit(createCreditRequestDto);
        CreateCreditResponseDto responseDto = new CreateCreditResponseDto(createdCredit.getCreditID());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<GetCreditsResponseDto> getCredits() {
        List<CreditResponseDto> allCredits = creditService.getCredits();
        GetCreditsResponseDto responseDto = new GetCreditsResponseDto();
        responseDto.setAllCredits(allCredits);
        return ResponseEntity.ok(responseDto);
    }
}
