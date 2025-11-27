package com.trendbazaar.service;

import com.trendbazaar.dto.ProductDto;
import com.trendbazaar.entity.Product;
import com.trendbazaar.exception.ResourceNotFoundException;
import com.trendbazaar.reposistory.ProduceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private  final ProduceRepository  produceRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDto create(ProductDto productDto) {
        if(productDto==null){
            throw  new  IllegalArgumentException("Product DTO is null");
        }
       Product product = modelMapper.map(productDto, Product.class);
       produceRepository.save(product);
       return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, long id) {


        Product product = produceRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found with id:"+id));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setStock(productDto.getStock());
        product.setImageUrl(productDto.getImageUrl());
        Product product1 = produceRepository.save(product);

        ProductDto productDto1  =  modelMapper.map(product , ProductDto.class);

        return productDto1;
    }

    @Override
    public void delete(long id) {
        boolean product = produceRepository.existsById(id);
        if(!product){
            throw new ResourceNotFoundException("product not found");
        }
        produceRepository.deleteById(id);

    }

    @Override
    public ProductDto getProductById(long id) {
        Product product = produceRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("product not found"));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productss = produceRepository.findAll();
        if(productss.isEmpty()){
            throw new ResourceNotFoundException("product not found");
        }
        List<ProductDto> productDtos = productss.stream().map((products)->modelMapper.map(products, ProductDto.class)).toList();
        return productDtos;
    }
}
