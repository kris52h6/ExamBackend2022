package dat.exam.api;

import dat.exam.dto.ProductDTO;
import dat.exam.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductDTO getProductById(@PathVariable int productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/name/{productName}")
    public ProductDTO getProductByName(@PathVariable String productName) {
        return productService.getProductByName(productName);
    }

    @PostMapping()
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @DeleteMapping("/{productId}")
    public ProductDTO deleteProduct(@PathVariable int productId) {
        return productService.deleteProductById(productId);
    }

    @PutMapping("/{productId}")
    public ProductDTO editProduct(@RequestBody ProductDTO productDTO, @PathVariable int productId) {
        return productService.editProduct(productDTO, productId);
    }



}
