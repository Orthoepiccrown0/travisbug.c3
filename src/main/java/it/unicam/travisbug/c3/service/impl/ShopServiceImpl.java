package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Shop;
import it.unicam.travisbug.c3.repository.ShopRepository;
import it.unicam.travisbug.c3.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shopService")
public class ShopServiceImpl implements ShopService {

    @Qualifier("shopRepository")
    private ShopRepository shopRepository;

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public void saveShop(Shop s) {
        shopRepository.save(s);
    }

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    @Override
    public void deleteShop(Shop shop){
        shopRepository.delete(shop);
    }

    @Override
    public Shop findById(Integer id) {
        return shopRepository.findById(id).orElseThrow();
    }
}
