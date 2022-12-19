package dat.exam.service;

import dat.exam.dto.ProductDTO;
import dat.exam.entity.Product;
import dat.exam.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    ProductService productService;

    @BeforeAll
    public static void setupData(@Autowired ProductRepository productRepository) {
        productRepository.save(new Product("Mælk", 10.50, 1000));
        productRepository.save(new Product("Toastbrød", 15, 500));
        productRepository.save(new Product("Chokolade", 24.99, 200));
    }

    @BeforeEach
    public void setupService() {
        productService = new ProductService(productRepository);
    }


    @Test
    void getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();

        assertEquals(3, products.size());
    }

    @Test
    void getProductById() {
        String productName = productService.getProductById(1).getName();

        assertEquals("Mælk", productName);
    }

    @Test
    void addProduct() {
        Product newProduct = new Product("Smør", 15, 200);
        ProductDTO productDTO = new ProductDTO(newProduct, false);
        productService.addProduct(productDTO);

        List<ProductDTO> products = productService.getAllProducts();

        assertThat(products, containsInAnyOrder(
                hasProperty("name", is("Smør")),
                hasProperty("name", is("Chokolade")),
                hasProperty("name", is("Mælk")),
                hasProperty("name", is("Toastbrød"))
        ));
    }

    @Test
    void deleteProductById() {
        productService.deleteProductById(1);

        assertEquals(2, productService.getAllProducts().size());

    }

    @Test
    void getProductByName() {
        ProductDTO product = productService.getProductByName("Toastbrød");

        assertEquals(15, product.getPrice());
    }



}