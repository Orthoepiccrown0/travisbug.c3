package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.shop.Category;
import it.unicam.travisbug.c3.model.shop.Product;
import it.unicam.travisbug.c3.model.users.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByMerchantOrderByNameAsc(Merchant merchant);

    List<Product> findAllByCategory(Category category);
}
