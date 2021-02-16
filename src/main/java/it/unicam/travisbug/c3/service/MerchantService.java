package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.users.Merchant;

import java.util.List;
import java.util.Optional;

public interface MerchantService {
    void saveMerchant(Merchant merchant);

    List<Merchant> getAll();

    Optional<Merchant> findById(String id);

    Merchant findByEmail(String email);

    void deleteMerchant(Merchant m);

    Merchant findByEmailAndPass(String email, String password);

}
