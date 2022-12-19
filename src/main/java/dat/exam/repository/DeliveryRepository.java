package dat.exam.repository;

import dat.exam.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    List<Delivery> findAllByVanNotNull();
    List<Delivery> findAllByVanId(int vanId);
}
