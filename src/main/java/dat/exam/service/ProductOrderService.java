package dat.exam.service;

import dat.exam.dto.ProductOrderDTO;
import dat.exam.entity.Delivery;
import dat.exam.entity.Product;
import dat.exam.entity.ProductOrder;
import dat.exam.repository.DeliveryRepository;
import dat.exam.repository.ProductOrderRepository;
import dat.exam.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductOrderService {

    ProductOrderRepository productOrderRepository;
    DeliveryRepository deliveryRepository;
    ProductRepository productRepository;

    public ProductOrderService(ProductOrderRepository productOrderRepository,
                               DeliveryRepository deliveryRepository,
                               ProductRepository productRepository) {
        this.productOrderRepository = productOrderRepository;
        this.deliveryRepository = deliveryRepository;
        this.productRepository = productRepository;
    }

    public List<ProductOrderDTO> getAllProductOrders() {
        return productOrderRepository.findAll().stream().map(po -> new ProductOrderDTO(po, true)).toList();
    }

    public ProductOrderDTO addProductOrder(ProductOrderDTO productOrderDTO) {
        Product productFound = productRepository.findByName(productOrderDTO.getProductName()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produkt med navn " + productOrderDTO.getProductName() + " ikke fundet"));
        Delivery deliveryFound = deliveryRepository
                .findById(productOrderDTO.getDeliveryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bestilling ikke fundet"));

        // Checks if delivery already contains a ProductOrder that specific product
        // and if it does, it adds to quantity instead of creating a new ProductOrder
        ProductOrder deliveryHasProduct = checkIfDeliveryHasProductOrder(productFound, deliveryFound);
        if(deliveryHasProduct != null) {
            deliveryHasProduct.addQuantity(productOrderDTO.getQuantity());
            productOrderRepository.save(deliveryHasProduct);
            return new ProductOrderDTO(deliveryHasProduct, false);
        }

        ProductOrder newProductOrder = ProductOrderDTO.getProductOrderEntity(productOrderDTO,productFound, deliveryFound);
        productOrderRepository.save(newProductOrder);
        return new ProductOrderDTO(newProductOrder, false);
    }

    private ProductOrder checkIfDeliveryHasProductOrder(Product product ,Delivery delivery) {
        ProductOrder productOrder = productOrderRepository.findByDeliveryIdAndProductId(delivery.getId(), product.getId());
        if(productOrder != null) {
            return productOrder;
        }
        return null;
    }

    public List<ProductOrderDTO> getProductOrdersByDeliveryId(int deliveryId) {
        return productOrderRepository.findAllByDeliveryId(deliveryId)
                .stream()
                .map(po -> new ProductOrderDTO(po, true))
                .toList();
    }
}
