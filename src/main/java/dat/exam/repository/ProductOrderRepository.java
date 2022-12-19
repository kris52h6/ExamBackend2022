package dat.exam.repository;

import dat.exam.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
    List<ProductOrder> findAllByDeliveryId(int deliveryId);
    ProductOrder findByDeliveryIdAndProductId(int deliveryId, int productId);
}
