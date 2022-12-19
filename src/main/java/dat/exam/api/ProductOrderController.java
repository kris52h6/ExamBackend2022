package dat.exam.api;

import dat.exam.dto.ProductOrderDTO;
import dat.exam.service.ProductOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/orders")
public class ProductOrderController {

    ProductOrderService productOrderService;

    public ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @GetMapping
    public List<ProductOrderDTO> getAllProductOrders() {
        return productOrderService.getAllProductOrders();
    }

    @GetMapping("/delivery/{deliveryId}")
    public List<ProductOrderDTO> getProductOrdersByDeliveryId(@PathVariable int deliveryId) {
        return productOrderService.getProductOrdersByDeliveryId(deliveryId);
    }

    @PostMapping
    public ProductOrderDTO addProductOrder(@RequestBody ProductOrderDTO productOrderDTO) {
        return productOrderService.addProductOrder(productOrderDTO);
    }
}
