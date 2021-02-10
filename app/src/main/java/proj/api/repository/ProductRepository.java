package proj.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import proj.api.model.Product;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByName(String name);
}
