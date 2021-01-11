package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Merchant;

import java.util.List;

public interface MerchantService {
    void saveMerchant(Merchant merchant);

    List<Merchant> getAll();
}
