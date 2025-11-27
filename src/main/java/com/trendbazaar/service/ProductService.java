package com.trendbazaar.service;

import com.trendbazaar.dto.ProductDto;
import com.trendbazaar.entity.Product;

import java.util.List;

public interface ProductService {

    ProductDto create(ProductDto productDto);
    ProductDto update(ProductDto productDto,long id);
    void delete(long id);
    ProductDto getProductById(long id);
    List<ProductDto> getAllProducts();
}
