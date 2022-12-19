package dat.exam.dto;

import dat.exam.entity.Delivery;
import dat.exam.entity.Product;
import dat.exam.entity.ProductOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductOrderDTO {
    private Integer id;
    private Integer quantity;
    private String productName;
    private Double productPrice;
    private Double productWeight;
    private Integer deliveryId;

    public static ProductOrder getProductOrderEntity(ProductOrderDTO productOrderDTO, Product product, Delivery delivery) {
        return new ProductOrder(
                productOrderDTO.getQuantity(),
                product,
                delivery
        );
    }

    public ProductOrderDTO(ProductOrder po, boolean includeAll) {
        this.quantity = po.getQuantity();
        this.productName = po.getProduct().getName();
        this.deliveryId = po.getDelivery().getId();
        this.productPrice = po.getProduct().getPrice();
        this.productWeight = po.getProduct().getWeight();
        if (includeAll) {
            this.id = po.getId();
        }
    }
}
