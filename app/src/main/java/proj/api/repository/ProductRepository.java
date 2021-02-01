package proj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.api.model.Product;
public interface ProductRepository extends JpaRepository<Product, Long> {
}
