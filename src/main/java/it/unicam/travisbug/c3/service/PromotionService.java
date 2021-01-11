package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Promotion;

import java.util.List;

public interface PromotionService {

    void savePromotion(Promotion p);

    List<Promotion> getAll();

}
