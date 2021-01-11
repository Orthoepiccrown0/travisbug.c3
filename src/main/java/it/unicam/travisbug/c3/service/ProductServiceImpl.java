package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Product;
import it.unicam.travisbug.c3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Qualifier("productRepository")
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product p) {
        productRepository.save(p);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
