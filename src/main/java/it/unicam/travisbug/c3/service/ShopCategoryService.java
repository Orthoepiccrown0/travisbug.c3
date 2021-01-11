package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.ShopCategory;

import java.util.List;

public interface ShopCategoryService {

    void saveShopCategory(ShopCategory c);

    List<ShopCategory> getAll();

}
