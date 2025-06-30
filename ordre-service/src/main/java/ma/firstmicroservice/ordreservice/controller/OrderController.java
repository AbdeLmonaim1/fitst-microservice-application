package ma.firstmicroservice.ordreservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.firstmicroservice.ordreservice.dto.OrderRequest;
import ma.firstmicroservice.ordreservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
@Slf4j
public class OrderController {
    private OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveOrder(@RequestBody OrderRequest orderRequest) {
        orderService.saveOrder(orderRequest);
        log.info("Saving Order");
        return "Order saved successfully";
    }


}
