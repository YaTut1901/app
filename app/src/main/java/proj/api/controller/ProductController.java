package proj.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.api.model.Product;
import proj.api.service.ProductService;
import proj.api.util.URIParamParser;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/product")
public class ProductController {
    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{name}")
    public Product getProduct(@PathVariable String name) {
        return service.getByName(name);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        service.save(product);
    }

    @GetMapping("/sort")
    public List<Product> getUsersOrderedBy(@RequestParam String param) {
        Map<String, Map<String, String>> paramsMap = URIParamParser.parse(param);
        return service.getProductSortedBy(paramsMap);
    }
}
