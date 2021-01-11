package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.repository.MerchantRepository;
import it.unicam.travisbug.c3.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class MerchantServiceImpl implements MerchantService {
    @Qualifier("merchantRepository")
    @Autowired
    private MerchantRepository merchantRepository;


    @Override
    public void saveMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    @Override
    public List<Merchant> getAll() {
        return merchantRepository.findAll();
    }
}
