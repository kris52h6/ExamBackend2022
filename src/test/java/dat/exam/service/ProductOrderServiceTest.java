package dat.exam.service;


import dat.exam.dto.ProductOrderDTO;
import dat.exam.entity.Delivery;
import dat.exam.entity.Product;
import dat.exam.entity.ProductOrder;
import dat.exam.repository.DeliveryRepository;
import dat.exam.repository.ProductOrderRepository;
import dat.exam.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductOrderServiceTest {

    @Autowired
    ProductOrderRepository productOrderRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    ProductRepository productRepository;

    ProductOrderService productOrderService;

    @BeforeAll
    public static void setupData(@Autowired ProductOrderRepository productOrderRepository, @Autowired DeliveryRepository deliveryRepository, @Autowired ProductRepository productRepository) {
        Product p1 = new Product("Mælk", 10.50, 1000);
        Product p2 = new Product("Toastbrød", 15, 500);
        Product p3 = new Product("Chokolade", 24.99, 200);

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);

        LocalDate ld = LocalDate.now();
        LocalDate tomorrow = ld.plusDays(1);

        Delivery d1 = new Delivery(tomorrow, "w1", "Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N");
        Delivery d2 = new Delivery(tomorrow, "w2", "Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N");
        deliveryRepository.save(d1);
        deliveryRepository.save(d2);

        ProductOrder po1 = new ProductOrder(1, p1, d1);
        ProductOrder po2 = new ProductOrder(4, p2, d1);
        ProductOrder po3 = new ProductOrder(4, p2, d2);
        productOrderRepository.save(po1);
        productOrderRepository.save(po2);
        productOrderRepository.save(po3);

    }


    @BeforeEach
    public void setupService() {
        this.productOrderService = new ProductOrderService(productOrderRepository, deliveryRepository, productRepository);
    }

    @Test
    void getAllProductOrders() {
        List<ProductOrderDTO> productOrderDTOS = productOrderService.getAllProductOrders();

        assertEquals(3, productOrderDTOS.size());
    }

    @Test
    void getProductOrdersByDeliveryId() {
        List<ProductOrderDTO> productOrderDTOS = productOrderService.getProductOrdersByDeliveryId(1);

        assertEquals(2, productOrderDTOS.size());
    }
}