package yin.com.stores.mapper;


import org.mapstruct.Mapper;
import yin.com.stores.dto.ProductDTO;
import yin.com.stores.model.Product;
@Mapper(componentModel = "spring")

public interface ProductMapper {
    Product toProduct(ProductDTO dto);
    ProductDTO toProductDTO(Product product);
}
