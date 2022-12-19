package dat.exam.service;

import dat.exam.dto.DeliveryDTO;
import dat.exam.entity.Delivery;
import dat.exam.repository.DeliveryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DeliveryService {

    DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<DeliveryDTO> getAllDeliveries() {
        return deliveryRepository.findAll().stream().map(d -> new DeliveryDTO(d, true)).toList();
    }

    public DeliveryDTO getDeliveryById(int deliveryId) {
        Delivery deliveryFound = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery not found"));
        return new DeliveryDTO(deliveryFound, true);
    }
}
