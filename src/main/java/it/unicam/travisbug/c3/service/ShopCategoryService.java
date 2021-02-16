package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.shop.ShopCategory;

import java.util.List;
import java.util.Optional;

public interface ShopCategoryService {

    void saveShopCategory(ShopCategory c);

    List<ShopCategory> getAll();

    Optional<ShopCategory> findById(Integer id);

}
