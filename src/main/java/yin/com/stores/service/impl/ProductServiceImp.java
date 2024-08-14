package yin.com.stores.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import yin.com.stores.dto.ProductDTO;
import yin.com.stores.exception.AppException;
import yin.com.stores.exception.ErrorCode;
import yin.com.stores.mapper.ProductMapper;
import yin.com.stores.model.Product;
import yin.com.stores.repository.ProductRepository;
import yin.com.stores.service.interf.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImp implements ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAllByDeleted(false);
        return products.stream().map(productMapper::toProductDTO).toList();
    }
    public ProductDTO createProduct(ProductDTO dto){
        if(productRepository.existsByName(dto.getName())){
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }
        Product product = productMapper.toProduct(dto);
        productRepository.save(product);
        return dto;
    }


}