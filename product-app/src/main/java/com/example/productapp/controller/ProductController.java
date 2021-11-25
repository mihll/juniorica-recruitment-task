package com.example.productapp.controller;

import com.example.productapp.dto.request.product.CreateProductRequestDto;
import com.example.productapp.dto.request.product.GetProductsRequestDto;
import com.example.productapp.dto.response.product.GetProductsResponseDto;
import com.example.productapp.dto.response.product.ProductResponseDto;
import com.example.productapp.model.Product;
import com.example.productapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody @Valid CreateProductRequestDto createProductRequestDto) {
        productService.createProduct(createProductRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/getAllForCreditIDs")
    public ResponseEntity<GetProductsResponseDto> getCustomers(@RequestBody @Valid GetProductsRequestDto getProductsRequestDto) {
        List<Product> products = productService.getAllForCreditIDs(getProductsRequestDto.getCreditIDs());
        GetProductsResponseDto responseDto = new GetProductsResponseDto();
        responseDto.setProducts(products
                .stream()
                .map(product -> {
                    ProductResponseDto productResponseDto = new ProductResponseDto();
                    productResponseDto.setCreditID(product.getCreditID());
                    productResponseDto.setProductName(product.getProductName());
                    productResponseDto.setProductValue(product.getProductValue());
                    return productResponseDto;
                })
                .collect(Collectors.toList())
        );
        return ResponseEntity.ok(responseDto);
    }
}
