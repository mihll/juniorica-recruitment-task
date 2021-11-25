package com.example.productapp.service;

import com.example.productapp.dto.request.product.CreateProductRequestDto;
import com.example.productapp.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequestDto CreateProductRequestDto);
    List<Product> getAllForCreditIDs(List<Integer> creditIDs);
}
