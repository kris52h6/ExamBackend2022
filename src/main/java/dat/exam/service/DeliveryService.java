package dat.exam.service;

import dat.exam.dto.DeliveryDTO;
import dat.exam.entity.Delivery;
import dat.exam.entity.Van;
import dat.exam.repository.DeliveryRepository;
import dat.exam.repository.VanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DeliveryService {

    DeliveryRepository deliveryRepository;
    VanRepository vanRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, VanRepository vanRepository) {
        this.deliveryRepository = deliveryRepository;
        this.vanRepository = vanRepository;
    }

    public List<DeliveryDTO> getAllDeliveries() {
        return deliveryRepository.findAll().stream().map(d -> new DeliveryDTO(d, true)).toList();
    }

    public DeliveryDTO getDeliveryById(int deliveryId) {
        Delivery deliveryFound = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Delivery not found"));
        return new DeliveryDTO(deliveryFound, true);
    }

    public DeliveryDTO addDelivery(DeliveryDTO deliveryDTO) {
        Delivery newDelivery = DeliveryDTO.getDeliveryEntity(deliveryDTO);
        deliveryRepository.save(newDelivery);
        return new DeliveryDTO(newDelivery, true);
    }

    public List<DeliveryDTO> getDeliveriesIfVanAssigned() {
        return deliveryRepository.findAllByVanNotNull().stream().map(d -> new DeliveryDTO(d, true)).toList();
    }

    public DeliveryDTO addDeliveryToVan(DeliveryDTO deliveryDTO, int vanId) {
        Delivery newDelivery = deliveryRepository.findById(deliveryDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bestilling ikke fundet"));
        List<Delivery> deliveries = deliveryRepository.findAllByVanId(vanId);
        Van van = vanRepository.findById(vanId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Van ikke fundet"));
        double currentVanWeight = 0;
        for (int i = 0; i < deliveries.size(); i++) {
            currentVanWeight += deliveries.get(i).getDeliveryWeight();
        }

        if (currentVanWeight + newDelivery.getDeliveryWeight() <= van.getCapacity()) {
            newDelivery.setVan(van);
            deliveryRepository.save(newDelivery);
            return new DeliveryDTO(newDelivery, true);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kapaciteten er overskredet");
        }
    }
}
