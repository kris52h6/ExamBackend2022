package dat.exam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Delivery delivery;

    public ProductOrder(int quantity, Product product, Delivery delivery) {
        this.quantity = quantity;
        this.product = product;
        this.delivery = delivery;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
