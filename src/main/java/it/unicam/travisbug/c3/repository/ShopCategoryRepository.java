package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.ShopCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCategoryRepository extends JpaRepository<ShopCategory, Integer> {
}
