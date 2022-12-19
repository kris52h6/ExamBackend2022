package dat.exam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Van {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;
    private String model;
    private double capacity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "van")
    private List<Delivery> deliveries;

    public Van(String brand, String model, double capacity) {
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
    }

    public void addDelivery(Delivery delivery) {
        this.deliveries.add(delivery);
    }
}
