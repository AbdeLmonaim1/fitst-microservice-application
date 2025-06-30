package ma.firstmicroservice.inventoryservice;

import ma.firstmicroservice.inventoryservice.entities.Inventory;
import ma.firstmicroservice.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
//    @Bean
    CommandLineRunner commandLineRunner(InventoryRepository inventoryRepository){
        return args -> {
            Inventory inventory = Inventory.builder()
                    .skuCode("iphone-13")
                    .quantity(10)
                    .build();
            inventoryRepository.save(inventory);
            Inventory inventory1 = Inventory.builder()
                    .skuCode("iphone-14")
                    .quantity(0)
                    .build();
            inventoryRepository.save(inventory1);
            Inventory inventory2 = Inventory.builder()
                    .skuCode("iphone-15")
                    .quantity(20)
                    .build();
            inventoryRepository.save(inventory2);
        };
    }
}
