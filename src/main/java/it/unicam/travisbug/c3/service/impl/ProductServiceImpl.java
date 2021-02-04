package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Category;
import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.model.Product;
import it.unicam.travisbug.c3.repository.ProductRepository;
import it.unicam.travisbug.c3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(Product p) {
        productRepository.save(p);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByMerchant(Merchant merchant) {
        return productRepository.findAllByMerchantOrderByNameAsc(merchant);
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow();
    }
}
