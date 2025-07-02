package ma.firstmicroservice.productservice.controller;

import lombok.AllArgsConstructor;
import ma.firstmicroservice.productservice.dto.ProductRequest;
import ma.firstmicroservice.productservice.dto.ProductResponse;
import ma.firstmicroservice.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello from ProductController";
    }
}
