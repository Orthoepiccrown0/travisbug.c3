package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.model.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(Product p);

    List<Product> getAll();

    List<Product> findAllByMerchant(Merchant merchant);

    Product findById(Integer id);
}
