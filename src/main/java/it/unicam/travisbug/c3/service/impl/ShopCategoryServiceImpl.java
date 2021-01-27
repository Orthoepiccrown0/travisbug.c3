package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.ShopCategory;
import it.unicam.travisbug.c3.repository.ShopCategoryRepository;
import it.unicam.travisbug.c3.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("shopCategoryService")
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Qualifier("shopCategoryRepository")
    private ShopCategoryRepository shopCategoryRepository;

    @Autowired
    public void setShopCategoryRepository(ShopCategoryRepository shopCategoryRepository) {
        this.shopCategoryRepository = shopCategoryRepository;
    }

    @Override
    public Optional<ShopCategory> findById(Integer id) {
        return shopCategoryRepository.findById(id);
    }

    @Override
    public void saveShopCategory(ShopCategory c) {
        shopCategoryRepository.save(c);
    }

    @Override
    public List<ShopCategory> getAll() {
        return shopCategoryRepository.findAll();
    }
}
