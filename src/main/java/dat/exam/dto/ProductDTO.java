package dat.exam.dto;

import dat.exam.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private double price;
    private double weight;
    private List<Integer> productOrderIds;

    public ProductDTO(Product p, boolean includeAll) {
        this.name = p.getName();
        this.price = p.getPrice();
        this.weight = p.getWeight();
        this.productOrderIds = p.getProductOrders().stream().map(o -> o.getId()).toList();
        if (includeAll) {
            this.id = p.getId();
        }
    }
}
