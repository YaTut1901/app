package proj.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proj.api.model.Product;
import proj.api.repository.ProductRepository;
import proj.api.util.Sequencer;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private ProductRepository repository;
    private Sequencer sequencer;

    @Autowired
    public ProductService(ProductRepository repository, Sequencer sequencer) {
        this.repository = repository;
        this.sequencer = sequencer;
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Product get(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Product> getProductSortedBy(Map<String, Map<String, String>> param) {
        return repository.findAll(sequencer.orderBy(param));
    }
}
