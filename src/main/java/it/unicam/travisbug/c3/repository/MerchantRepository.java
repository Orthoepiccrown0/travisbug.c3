package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.users.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {

    Merchant findByEmail(String email);
    Merchant findByEmailAndPassword(String email, String password);
}
