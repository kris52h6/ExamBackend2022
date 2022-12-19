package dat.exam.dto;

import dat.exam.entity.Delivery;
import dat.exam.entity.Van;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VanDTO {
    private Integer id;
    private String brand;
    private String model;
    private double currentCapacity;
    private double maxCapacity;

    public static Van getVanEntity(VanDTO vanDTO) {
        return new Van(
                vanDTO.getBrand(),
                vanDTO.getModel(),
                vanDTO.getMaxCapacity()
        );
    }

    public VanDTO(Van van) {
        this.id = van.getId();
        this.brand = van.getBrand();
        this.model = van.getModel();
        this.currentCapacity = van.getDeliveries().stream().map(Delivery::getDeliveryWeight).reduce(0.0, Double::sum);
        this.maxCapacity = van.getCapacity();
    }
}
