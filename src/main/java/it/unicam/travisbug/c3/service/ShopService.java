package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Shop;

import java.util.List;

public interface ShopService {

    void saveShop(Shop s);

    List<Shop> getAll();

    void deleteShop(Shop s);

    Shop findById(Integer id);

}
