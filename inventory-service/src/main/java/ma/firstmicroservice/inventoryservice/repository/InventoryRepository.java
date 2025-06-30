package ma.firstmicroservice.inventoryservice.repository;

import ma.firstmicroservice.inventoryservice.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCodes);
    Inventory findBySkuCode(String skuCode);
}