package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.AdminRequests;
import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.model.Promotion;
import it.unicam.travisbug.c3.model.Shop;
import it.unicam.travisbug.c3.repository.AdminRequestsRepository;
import it.unicam.travisbug.c3.repository.MerchantRepository;
import it.unicam.travisbug.c3.repository.PromotionRepository;
import it.unicam.travisbug.c3.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class Administration {

    private AdminRequestsRepository adminRequestsRepository;

    private ShopRepository shopRepository;

    private PromotionRepository promotionRepository;

    private MerchantRepository merchantRepository;

    @Autowired
    public void setMerchantRepository(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Autowired
    public void setPromotionRepository(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Autowired
    public void setAdminRequestsRepository(AdminRequestsRepository adminRequestsRepository) {
        this.adminRequestsRepository = adminRequestsRepository;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<AdminRequests> requests = adminRequestsRepository.findAllByOrderByDateDesc();
        model.addAttribute("requests", requests);
        return "admin";
    }

    @GetMapping("/admin/accept/{id}")
    public String accept(@PathVariable String id) {
        AdminRequests request = adminRequestsRepository.findById(id).orElseThrow();
        Shop shop = request.getShop();
        Promotion promotion = request.getPromotion();
        if (shop != null) {
            shop.setApproved(true);
            shopRepository.save(shop);
        } else if (promotion != null) {
            promotion.setApproved(true);
            promotionRepository.save(promotion);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/decline/{id}")
    public String decline(@PathVariable String id) {
        AdminRequests request = adminRequestsRepository.findById(id).orElseThrow();
        Shop shop = request.getShop();
        Promotion promotion = request.getPromotion();
        if (shop != null) {
            for (Merchant merchant: merchantRepository.findAll()) {
                if(merchant.getShop().getId().equals(shop.getId()))
                    adminRequestsRepository.delete(request);
                    shopRepository.delete(shop);
                    merchantRepository.delete(merchant);
            }
//            merchantRepository.delete(merchant);
//            shopRepository.delete(shop);
        } else if (promotion != null) {
            promotionRepository.delete(promotion);
        }
        return "redirect:/admin";
    }

}
