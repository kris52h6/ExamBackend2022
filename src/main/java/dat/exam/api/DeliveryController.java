package dat.exam.api;

import dat.exam.dto.DeliveryDTO;
import dat.exam.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/deliveries")
public class DeliveryController {

    DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public List<DeliveryDTO> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{deliveryId}")
    public DeliveryDTO getDeliveryById(@PathVariable int deliveryId) {
        return deliveryService.getDeliveryById(deliveryId);
    }

    @PostMapping
    public DeliveryDTO addDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        return deliveryService.addDelivery(deliveryDTO);
    }

    @GetMapping("/assigned")
    public List<DeliveryDTO> getDeliveriesIfVanAssigned() {
        return deliveryService.getDeliveriesIfVanAssigned();
    }

    @PostMapping("/van/{vanId}")
    public DeliveryDTO addDeliveryToVan(@RequestBody DeliveryDTO deliveryDTO, @PathVariable int vanId) {
        return deliveryService.addDeliveryToVan(deliveryDTO, vanId);
    }
}
