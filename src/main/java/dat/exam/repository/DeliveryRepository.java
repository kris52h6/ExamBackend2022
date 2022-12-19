package dat.exam.repository;

import dat.exam.entity.Delivery;
import dat.exam.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
}
