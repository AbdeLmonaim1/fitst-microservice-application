package ma.firstmicroservice.productservice.repository;

import ma.firstmicroservice.productservice.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
