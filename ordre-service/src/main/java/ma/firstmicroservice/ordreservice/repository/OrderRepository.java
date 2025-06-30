package ma.firstmicroservice.ordreservice.repository;

import ma.firstmicroservice.ordreservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
