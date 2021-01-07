package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Shop;
import it.unicam.travisbug.c3.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shopService")
public class ShopServiceImpl implements ShopService {

    @Qualifier("shopRepository")
    @Autowired
    private ShopRepository shopRepository;

    @Override
    public void saveShop(Shop s) {
        shopRepository.save(s);
    }

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }
}
