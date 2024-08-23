package yin.com.stores.service.interf;
import yin.com.stores.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getProducts();
    public ProductDTO getProductById(String productId);
    public ProductDTO getProductByName(String productName);

    public ProductDTO createProduct(ProductDTO dto);
    public ProductDTO updateProduct(String productId, ProductDTO dto);

    public void deleteProduct(String productId);
}
