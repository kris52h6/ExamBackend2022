package dat.exam.service;

import dat.exam.dto.ProductDTO;
import dat.exam.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(p -> new ProductDTO(p, true)).toList();
    }
}
