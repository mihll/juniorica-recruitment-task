package com.example.productapp.service;

import com.example.productapp.dto.request.product.CreateProductRequestDto;
import com.example.productapp.model.Product;
import com.example.productapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createProduct(CreateProductRequestDto createProductRequestDto) {
        Product productToCreate = new Product();
        productToCreate.setCreditID(createProductRequestDto.getCreditID());
        productToCreate.setProductName(createProductRequestDto.getProductName());
        productToCreate.setProductValue(createProductRequestDto.getProductValue());

        return productRepository.save(productToCreate);
    }

    @Override
    public List<Product> getAllForCreditIDs(List<Integer> creditIDs) {
        return productRepository.findAllById(creditIDs);
    }
}
