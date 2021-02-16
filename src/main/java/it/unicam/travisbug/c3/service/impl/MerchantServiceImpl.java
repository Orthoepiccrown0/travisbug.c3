package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.users.Merchant;
import it.unicam.travisbug.c3.repository.MerchantRepository;
import it.unicam.travisbug.c3.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {
    @Qualifier("merchantRepository")
    private MerchantRepository merchantRepository;

    @Autowired
    public void setMerchantRepository(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
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

    @Override
    public Merchant findByEmail(String email) {
        return merchantRepository.findByEmail(email);
    }

    @Override
    public void deleteMerchant(Merchant merchant) {
        merchantRepository.delete(merchant);
    }

    @Override
    public Merchant findByEmailAndPass(String email, String password) {
        return merchantRepository.findByEmailAndPassword(email,password);
    }
}
