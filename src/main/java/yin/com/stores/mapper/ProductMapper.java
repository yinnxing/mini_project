package yin.com.stores.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import yin.com.stores.dto.ProductDTO;
import yin.com.stores.model.Product;

@Mapper(componentModel = "spring")

public interface ProductMapper {
    Product toProduct(ProductDTO dto);
    ProductDTO toProductDTO(Product product);
    @Mapping(target = "productId", ignore = true)

    void updateProduct(@MappingTarget Product product, ProductDTO dto);
}
