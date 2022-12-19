package dat.exam.dto;

import dat.exam.entity.Delivery;
import dat.exam.entity.ProductOrder;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryDTO {
    private Integer id;
    private LocalDate deliveryDate;
    private String fromWareHouse;
    private String destination;
    private List<Integer> productOrderIds;

    public DeliveryDTO(Delivery d, boolean includeAll) {
        this.deliveryDate = d.getDeliveryDate();
        this.fromWareHouse = d.getFromWareHouse();
        this.destination = d.getDestination();
        this.productOrderIds = d.getProductOrderList().stream().map(o -> o.getId()).toList();
        if (includeAll) {
            this.id = d.getId();
        }
    }
}