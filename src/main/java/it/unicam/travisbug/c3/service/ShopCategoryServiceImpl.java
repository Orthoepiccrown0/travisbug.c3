package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.ShopCategory;
import it.unicam.travisbug.c3.repository.ShopCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Qualifier("shopCategoryRepository")
    @Autowired
    private ShopCategoryRepository shopCategoryRepository;

    @Override
    public void saveShopCategory(ShopCategory c) {
        shopCategoryRepository.save(c);
    }

    @Override
    public List<ShopCategory> getAll() {
        return shopCategoryRepository.findAll();
    }
}
