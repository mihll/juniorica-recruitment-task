package com.example.creditapp.service;

import com.example.creditapp.dto.request.credit.CreateCreditRequestDto;
import com.example.creditapp.dto.request.customer.CreateCustomerRequestDto;
import com.example.creditapp.dto.request.customer.GetCustomersRequestDto;
import com.example.creditapp.dto.request.product.CreateProductRequestDto;
import com.example.creditapp.dto.request.product.GetProductsRequestDto;
import com.example.creditapp.dto.response.credit.CreditResponseDto;
import com.example.creditapp.dto.response.customer.CustomerResponseDto;
import com.example.creditapp.dto.response.customer.GetCustomersResponseDto;
import com.example.creditapp.dto.response.product.GetProductsResponseDto;
import com.example.creditapp.dto.response.product.ProductResponseDto;
import com.example.creditapp.model.Credit;
import com.example.creditapp.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    CreditRepository creditRepository;

    private final WebClient productWebClient = WebClient.create("http://product-app:8082/product");
    private final WebClient customerWebClient = WebClient.create("http://customer-app:8083/customer");

    @Override
    public Credit createCredit(CreateCreditRequestDto createCreditRequestDto) {
        // 1 generate random credit number
        SecureRandom secureRandom = new SecureRandom();
        Integer newCreditNumber;
        do {
            newCreditNumber = secureRandom.nextInt(Integer.MAX_VALUE);
        } while (creditRepository.findById(newCreditNumber).isPresent());

        // 2 call CreateProduct
        callCreateProduct(newCreditNumber, createCreditRequestDto);

        // 3 call CreateCustomer
        callCreateCustomer(newCreditNumber, createCreditRequestDto);

        // 4 create Credit entity
        Credit creditToCreate = new Credit();
        creditToCreate.setCreditID(newCreditNumber);
        creditToCreate.setCreditName(createCreditRequestDto.getCreditName());

        // 5 save to DB and return Credit
        return creditRepository.save(creditToCreate);
    }

    @Override
    public List<CreditResponseDto> getCredits() {
        // 1 get all credits from DB
        List<Credit> allCredits = creditRepository.findAll();

        // 2 get Customers for credit IDs
        GetCustomersResponseDto receivedCustomers = callGetCustomers(allCredits);

        // 3 get Products for credit IDs
        GetProductsResponseDto receivedProducts = callGetProducts(allCredits);

        return allCredits.stream()
                .map(credit -> {
                    // find customer for credit ID
                    CustomerResponseDto customerForCredit = receivedCustomers.getCustomers()
                            .stream()
                            .filter(customer -> customer.getCreditID().equals(credit.getCreditID()))
                            .findAny().orElseThrow();

                    // find product for credit ID
                    ProductResponseDto productForCredit = receivedProducts.getProducts()
                            .stream()
                            .filter(product -> product.getCreditID().equals(credit.getCreditID()))
                            .findAny().orElseThrow();

                    // build response DTO
                    CreditResponseDto creditResponseDto = new CreditResponseDto();
                    creditResponseDto.setCreditName(credit.getCreditName());
                    creditResponseDto.setClientFirstName(customerForCredit.getCustomerFirstName());
                    creditResponseDto.setClientLastName(customerForCredit.getCustomerLastName());
                    creditResponseDto.setClientPESEL(customerForCredit.getCustomerPESEL());
                    creditResponseDto.setProductName(productForCredit.getProductName());
                    creditResponseDto.setProductValue(productForCredit.getProductValue());

                    return creditResponseDto;
                })
                .collect(Collectors.toList());
    }

    private void callCreateProduct(Integer creditNumber, CreateCreditRequestDto createCreditRequestDto) {
        CreateProductRequestDto createProductRequestDto = new CreateProductRequestDto();
        createProductRequestDto.setCreditID(creditNumber);
        createProductRequestDto.setProductName(createCreditRequestDto.getProductName());
        createProductRequestDto.setProductValue(createCreditRequestDto.getProductValue());

        productWebClient.post()
                .uri("/create")
                .bodyValue(createProductRequestDto)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    private void callCreateCustomer(Integer creditNumber, CreateCreditRequestDto createCreditRequestDto) {
        CreateCustomerRequestDto createCustomerRequestDto = new CreateCustomerRequestDto();
        createCustomerRequestDto.setCreditID(creditNumber);
        createCustomerRequestDto.setCustomerFirstName(createCreditRequestDto.getCustomerFirstName());
        createCustomerRequestDto.setCustomerLastName(createCreditRequestDto.getCustomerLastName());
        createCustomerRequestDto.setCustomerPESEL(createCreditRequestDto.getCustomerPESEL());

        customerWebClient.post()
                .uri("/create")
                .bodyValue(createCustomerRequestDto)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    private GetCustomersResponseDto callGetCustomers(List<Credit> allCredits) {
        GetCustomersRequestDto getCustomersRequestDto = new GetCustomersRequestDto();
        getCustomersRequestDto.setCreditIDs(allCredits
                .stream()
                .map(Credit::getCreditID)
                .collect(Collectors.toList())
        );

        return customerWebClient.post()
                .uri("/getAllForCreditIDs")
                .bodyValue(getCustomersRequestDto)
                .retrieve()
                .bodyToMono(GetCustomersResponseDto.class)
                .block();
    }

    private GetProductsResponseDto callGetProducts(List<Credit> allCredits) {
        GetProductsRequestDto getProductsRequestDto = new GetProductsRequestDto();
        getProductsRequestDto.setCreditIDs(allCredits
                .stream()
                .map(Credit::getCreditID)
                .collect(Collectors.toList())
        );

        return productWebClient.post()
                .uri("/getAllForCreditIDs")
                .bodyValue(getProductsRequestDto)
                .retrieve()
                .bodyToMono(GetProductsResponseDto.class)
                .block();
    }
}
