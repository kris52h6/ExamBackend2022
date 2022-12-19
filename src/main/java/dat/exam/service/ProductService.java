package dat.exam.service;

import dat.exam.dto.ProductDTO;
import dat.exam.entity.Product;
import dat.exam.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public ProductDTO getProductById(int productId) {
        Product productFound = productRepository
                .findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produkt med id " + productId + " ikke fundet"));
        return new ProductDTO(productFound, true);
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        Product newProduct = productDTO.getProductEntity(productDTO);
        productRepository.save(newProduct);
        return new ProductDTO(newProduct, true);
    }

    public ProductDTO deleteProduct(int productId) {
        Product productToBeDeleted = productRepository
                .findById(productId)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produkt med id " + productId + " ikke fundet"));
        productRepository.delete(productToBeDeleted);
        return new ProductDTO(productToBeDeleted, true);
    }

    public ProductDTO getProductByName(String productName) {
        Product product = productRepository
                .findByName(productName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produkt med navn: " + productName + " ikke fundet"));
        return new ProductDTO(product, true);
    }
}
