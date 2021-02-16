package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.shop.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
}
