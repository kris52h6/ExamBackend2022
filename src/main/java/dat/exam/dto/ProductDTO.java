package dat.exam.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat.exam.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Integer id;
    private String name;
    private double price;
    private double weight;
    private List<Integer> productOrderIds = new ArrayList<>();

    public static Product getProductEntity(ProductDTO productDTO) {
        return new Product(
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getWeight()
        );
    }

    public ProductDTO(Product p, boolean includeAll) {
        this.name = p.getName();
        this.price = p.getPrice();
        this.weight = p.getWeight();
        if (p.getProductOrders() != null) {
            this.productOrderIds = p.getProductOrders().stream().map(o -> o.getId()).toList();
        }

        if (includeAll) {
            this.id = p.getId();
        }
    }
}
