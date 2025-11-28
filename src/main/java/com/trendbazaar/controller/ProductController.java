package com.trendbazaar.controller;


import com.trendbazaar.dto.ProductDto;
import com.trendbazaar.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto)
    {
        ProductDto productDto1 = productService.create(productDto);
        return ResponseEntity.ok().body(productDto1);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>>   getAllProducts()
    {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public   ResponseEntity<ProductDto> getProductById(@PathVariable  long id)
    {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }
    @PutMapping("/{id}")
    public   ResponseEntity<ProductDto> updateProduct(@PathVariable long id,@RequestBody ProductDto productDto)
    {
     ProductDto productDto1 =  productService.update(productDto,id);
     return new ResponseEntity<>(productDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public   void deleteProductById(@PathVariable  long id)
    {
        productService.delete(id);
    }

}
