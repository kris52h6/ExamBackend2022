package dat.exam.repository;

import dat.exam.entity.Van;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VanRepository extends JpaRepository<Van, Integer> {
}
