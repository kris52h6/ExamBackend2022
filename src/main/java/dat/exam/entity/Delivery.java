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
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fromWareHouse;

    private String destination;

    @OneToMany(mappedBy = "delivery")
    private List<ProductOrder> productOrderList;

    public Delivery(String fromWareHouse, String destination) {
        this.fromWareHouse = fromWareHouse;
        this.destination = destination;
    }
}
