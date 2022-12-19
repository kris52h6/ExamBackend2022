package dat.exam.dto;

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
    private Integer productId;
    private Integer deliveryId;

    public ProductOrderDTO(ProductOrder po, boolean includeAll) {
        this.quantity = po.getQuantity();
        this.productId = po.getProduct().getId();
        this.deliveryId = po.getDelivery().getId();
        if (includeAll) {
            this.id = po.getId();
        }
    }
}
