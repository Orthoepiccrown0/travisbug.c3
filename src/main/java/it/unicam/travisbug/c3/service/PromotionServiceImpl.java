package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Promotion;
import it.unicam.travisbug.c3.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class PromotionServiceImpl implements PromotionService {

    @Qualifier("promotionRepository")
    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public void savePromotion(Promotion p) {
        promotionRepository.save(p);
    }

    @Override
    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }
}
