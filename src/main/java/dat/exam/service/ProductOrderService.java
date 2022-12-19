package dat.exam.service;

import dat.exam.repository.ProductOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderService {

    ProductOrderRepository productOrderRepository;

    public ProductOrderService(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }
}
