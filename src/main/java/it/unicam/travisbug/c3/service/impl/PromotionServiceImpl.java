package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Promotion;
import it.unicam.travisbug.c3.repository.PromotionRepository;
import it.unicam.travisbug.c3.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("promotionService")
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
