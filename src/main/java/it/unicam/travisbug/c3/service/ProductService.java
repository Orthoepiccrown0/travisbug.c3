package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.shop.Category;
import it.unicam.travisbug.c3.model.users.Merchant;
import it.unicam.travisbug.c3.model.shop.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(Product p);

    List<Product> getAll();

    List<Product> findAllByMerchant(Merchant merchant);

    List<Product> findAllByCategory(Category category);

    Product findById(Integer id);

    void deleteProduct(Product p);

}
