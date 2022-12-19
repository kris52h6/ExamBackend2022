package dat.exam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryDTO {
    private Integer id;
    private LocalDate deliveryDate;
    private String fromWareHouse;
    private String destination;
    private List<Integer> productOrderIds;
    private Integer vanId;

    public static Delivery getDeliveryEntity(DeliveryDTO d) {
        return new Delivery(
                d.getDeliveryDate(),
                d.getFromWareHouse(),
                d.getDestination()
        );
    }

    public DeliveryDTO(Delivery d, boolean includeAll) {
        this.deliveryDate = d.getDeliveryDate();
        this.fromWareHouse = d.getFromWareHouse();
        this.destination = d.getDestination();
        if (d.getVan() != null) {
            this.vanId = d.getVan().getId();
        }
        if (d.getProductOrderList() != null) {
            this.productOrderIds = d.getProductOrderList().stream().map(o -> o.getId()).toList();
        }
        if (includeAll) {
            this.id = d.getId();
        }
    }
}