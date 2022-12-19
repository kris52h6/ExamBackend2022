package dat.exam.configuration;

import dat.exam.entity.Delivery;
import dat.exam.entity.Product;
import dat.exam.entity.ProductOrder;
import dat.exam.repository.DeliveryRepository;
import dat.exam.repository.ProductOrderRepository;
import dat.exam.repository.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class SetupDevData implements ApplicationRunner {

    ProductRepository productRepository;
    DeliveryRepository deliveryRepository;
    ProductOrderRepository productOrderRepository;

    public SetupDevData(ProductRepository productRepository,
                        DeliveryRepository deliveryRepository,
                        ProductOrderRepository productOrderRepository) {
        this.productRepository = productRepository;
        this.deliveryRepository = deliveryRepository;
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        setupData();
    }

    private void setupData() {
        Product p1 = new Product("Mælk", 10.50, 1000);
        Product p2 = new Product("Toastbrød", 15, 500);
        Product p3 = new Product("Chokolade", 24.99, 200);

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);

        LocalDate ld = LocalDate.now();
        LocalDate tomorrow = ld.plusDays(1);

        Delivery d1 = new Delivery(tomorrow, "w1", "Ninna Jørgensen, Nørrebrogade 23, 2 tv, 2200 N");
        deliveryRepository.save(d1);

        ProductOrder po1 = new ProductOrder(1, p1, d1);
        ProductOrder po2 = new ProductOrder(4, p2, d1);
        productOrderRepository.save(po1);
        productOrderRepository.save(po2);

    }

}
