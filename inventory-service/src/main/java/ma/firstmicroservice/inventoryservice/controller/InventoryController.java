package ma.firstmicroservice.inventoryservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.firstmicroservice.inventoryservice.dto.InventoryResponse;
import ma.firstmicroservice.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
@Slf4j
public class InventoryController {
    public InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Inventory Controller";
    }
//@GetMapping("/{sku-code}")
//@ResponseStatus(HttpStatus.OK)
//public boolean isInStock(@PathVariable("sku-code") String skuCode) {
//    log.info("Received inventory check request for skuCode: {}", skuCode);
//    return inventoryService.isInStock(skuCode);
//}
}
