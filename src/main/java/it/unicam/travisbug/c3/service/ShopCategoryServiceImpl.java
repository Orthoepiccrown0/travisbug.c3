package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.ShopCategory;
import it.unicam.travisbug.c3.repository.ShopCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shopCategoryService")
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
