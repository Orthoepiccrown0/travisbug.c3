package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("shopRepository")
public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
