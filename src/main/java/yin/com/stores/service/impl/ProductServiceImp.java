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
import yin.com.stores.model.StoreProduct;
import yin.com.stores.repository.ProductRepository;
import yin.com.stores.repository.StoreProductRepository;
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

    @Override
    public ProductDTO getProductById(String productId) {
        Product product = productRepository.findByProductIdAndDeletedFalse(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toProductDTO(product);
    }
    public ProductDTO getProductByName(String productName) {
        Product product = productRepository.findByNameAndDeletedFalse(productName)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toProductDTO(product);
    }

    public ProductDTO createProduct(ProductDTO dto){
        if(productRepository.existsByName(dto.getName())){
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }
        Product product = productMapper.toProduct(dto);
        productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    @Override
    public ProductDTO updateProduct(String productId, ProductDTO dto) {
        Product product = productRepository.findByProductIdAndDeletedFalse(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        productMapper.updateProduct(product, dto);
        productRepository.save(product);
        return productMapper.toProductDTO(product);

    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findByProductIdAndDeletedFalse(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setDeleted(true);
        productRepository.save(product);

    }


}