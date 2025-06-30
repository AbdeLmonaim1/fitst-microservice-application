package ma.firstmicroservice.ordreservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDTO {
    private Long id;
    private String skuCode;
    private double price;
    private int quantity;
}
