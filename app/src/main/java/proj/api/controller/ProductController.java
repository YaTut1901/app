package proj.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.api.ampq.Producer;
import proj.api.model.Product;
import proj.api.service.ProductService;
import proj.api.util.URIParamParser;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/product")
public class ProductController {
    private ProductService service;
    private Producer producer;

    public ProductController(ProductService service, Producer producer) {
        this.service = service;
        this.producer = producer;
    }

    @GetMapping("/{name}")
    public Product getProduct(@PathVariable String name) {
        return service.getByName(name);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        service.save(product);
        producer.sendMessage("product added: Id: " + product.getId()
                + ", Manufacture: " + product.getManufacture()
                + ", Name: " + product.getName()
                + ", Type: " + product.getType(), "product.created");
    }

    @GetMapping("/sort")
    public List<Product> getUsersOrderedBy(@RequestParam String param) {
        Map<String, Map<String, String>> paramsMap = URIParamParser.parse(param);
        return service.getProductSortedBy(paramsMap);
    }
}
