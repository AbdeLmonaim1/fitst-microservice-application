package ma.firstmicroservice.inventoryservice.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.firstmicroservice.inventoryservice.dto.InventoryResponse;
import ma.firstmicroservice.inventoryservice.entities.Inventory;
import ma.firstmicroservice.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryService {
    private InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Checking Inventory");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
//    public boolean isInStock(String skuCode) {
//        Inventory bySkuCode = inventoryRepository.findBySkuCode(skuCode);
//        return bySkuCode != null && bySkuCode.getQuantity() > 0;
//    }
}
