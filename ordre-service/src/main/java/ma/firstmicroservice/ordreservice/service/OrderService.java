package ma.firstmicroservice.ordreservice.service;

import lombok.AllArgsConstructor;
import ma.firstmicroservice.ordreservice.dto.InventoryResponse;
import ma.firstmicroservice.ordreservice.dto.OrderRequest;
import ma.firstmicroservice.ordreservice.entities.Order;
import ma.firstmicroservice.ordreservice.entities.OrderItems;
import ma.firstmicroservice.ordreservice.mappers.Mapper;
import ma.firstmicroservice.ordreservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@AllArgsConstructor
public class OrderService {
    private Mapper mapper;
    private OrderRepository orderRepository;
    private WebClient.Builder webClientBuilder;
    public String saveOrder(OrderRequest orderRequest){
        // 1. Convert OrderRequest to Order
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        // 2. Convert OrderItemDTO to OrderItem
        List<OrderItems> orderItemsList = orderRequest.getOrderItemsdto()
                .stream().map(mapper::toOrderItems).toList();
        order.setOrderItems(orderItemsList);
        List<String> skuCodes = order.getOrderItems().stream()
                .map(OrderItems::getSkuCode).toList();
        //Call inventory service, and place the order if the item is in the stock
         InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
                        )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        System.out.println("Inventory response :" + inventoryResponses);

        List<String> skuCodeFromInventory = new ArrayList<>();

        for (InventoryResponse inventoryResponse : inventoryResponses) {
            skuCodeFromInventory.add(inventoryResponse.getSkuCode());
        }
        Set<String> set1 = new HashSet<>(skuCodes);
        Set<String> set2 = new HashSet<>(skuCodeFromInventory);

        boolean areEqual = set1.equals(set2);
        System.out.println("Are the two lists equal? " + areEqual);

        boolean allItemsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if(allItemsInStock && areEqual) {
            orderRepository.save(order);
        } else if (!areEqual) {
            throw new IllegalArgumentException("Product is not exists in stock, please try again");
        }else {
            throw new IllegalArgumentException("Product is not available in stock, please try again");
        }

        return "Done!";
    }
}
