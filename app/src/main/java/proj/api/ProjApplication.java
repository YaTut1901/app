package proj.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import proj.api.model.Product;
import proj.api.service.ProductService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ProjApplication {
    private ProductService service;

    @Autowired
    public ProjApplication(ProductService service) {
        this.service = service;
    }

    @PostConstruct
    public void init() {
        Product product = new Product("name_1", "1234", "type_2");
        Product product1 = new Product("name_2", "1234", "type_4");
        Product product2 = new Product("name_3", "1234", "type_3");
        Product product3 = new Product("name_4", "1234", "type_6");
        Product product4 = new Product("name_5", "1234", "type_5");
        service.save(product);
        service.save(product1);
        service.save(product2);
        service.save(product3);
        service.save(product4);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjApplication.class, args);
    }

}
