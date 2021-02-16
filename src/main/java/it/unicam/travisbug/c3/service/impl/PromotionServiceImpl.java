package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.shop.Promotion;
import it.unicam.travisbug.c3.repository.PromotionRepository;
import it.unicam.travisbug.c3.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("promotionService")
public class PromotionServiceImpl implements PromotionService {

    @Qualifier("promotionRepository")
    private PromotionRepository promotionRepository;

    @Autowired
    public void setPromotionRepository(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public void savePromotion(Promotion p) {
        promotionRepository.save(p);
    }

    @Override
    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }

    @Override
    public void deletePromotion(Promotion promotion){
        promotionRepository.delete(promotion);
    }

}

