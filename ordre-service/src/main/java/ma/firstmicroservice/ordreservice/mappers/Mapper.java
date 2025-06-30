package ma.firstmicroservice.ordreservice.mappers;

import ma.firstmicroservice.ordreservice.dto.OrderItemsDTO;
import ma.firstmicroservice.ordreservice.entities.OrderItems;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public OrderItems toOrderItems(OrderItemsDTO orderItemsDto) {
        OrderItems orderItems = new OrderItems();
        orderItems.setPrice(orderItemsDto.getPrice());
        orderItems.setQuantity(orderItemsDto.getQuantity());
        orderItems.setSkuCode(orderItemsDto.getSkuCode());
        return orderItems;
    }
}
