package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.repository.MerchantRepository;
import it.unicam.travisbug.c3.service.CourierService;
import it.unicam.travisbug.c3.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {
    @Qualifier("merchantRepository")
    @Autowired
    private MerchantRepository merchantRepository;

    private static MerchantServiceImpl merchantService;

    private MerchantServiceImpl() {

    }

    
    public static MerchantService getServiceInstance() {
        if (merchantService == null)
            merchantService = new MerchantServiceImpl();
        return merchantService;
    }

    @Override
    public void saveMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    @Override
    public List<Merchant> getAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Optional<Merchant> findById(String id) {
        return merchantRepository.findById(id);
    }
}
