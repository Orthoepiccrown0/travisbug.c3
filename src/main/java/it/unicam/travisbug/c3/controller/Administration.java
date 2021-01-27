package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.AdminRequests;
import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.model.Promotion;
import it.unicam.travisbug.c3.model.Shop;
import it.unicam.travisbug.c3.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class Administration {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<AdminRequests> requests = dbManager.getAdminRequestsService().findAllByOrderByDateDesc();
        model.addAttribute("requests", requests);
        return "admin";
    }

    @GetMapping("/admin/accept/{id}")
    public String accept(@PathVariable String id) {
        AdminRequests request = dbManager.getAdminRequestsService().findById(id).orElseThrow();
        Shop shop = request.getShop();
        Promotion promotion = request.getPromotion();
        if (shop != null) {
            shop.setApproved(true);
            dbManager.getShopService().saveShop(shop);
            dbManager.getAdminRequestsService().deleteAdminRequest(request);
        } else if (promotion != null) {
            promotion.setApproved(true);
            dbManager.getPromotionService().savePromotion(promotion);
            dbManager.getAdminRequestsService().deleteAdminRequest(request);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/decline/{id}")
    public String decline(@PathVariable String id) {
        AdminRequests request = dbManager.getAdminRequestsService().findById(id).orElseThrow();
        Shop shop = request.getShop();
        Promotion promotion = request.getPromotion();
        if (shop != null) {
            for (Merchant merchant: dbManager.getMerchantService().getAll()) {
                if(merchant.getShop().getId().equals(shop.getId()))
                    dbManager.getAdminRequestsService().deleteAdminRequest(request);
                    dbManager.getShopService().deleteShop(shop);
                    dbManager.getMerchantService().deleteMerchant(merchant);
            }
        } else if (promotion != null) {
            dbManager.getPromotionService().deletePromotion(promotion);
        }
        return "redirect:/admin";
    }

}
