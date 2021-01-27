package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.model.Product;
import it.unicam.travisbug.c3.model.Promotion;
import it.unicam.travisbug.c3.model.Shop;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
public class ShopArea {

    private DBManager dbManager;

    private final AppCookies appCookies = new AppCookies();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/shop/{id}")
    public String showShop(Model model, @PathVariable Integer id, @CookieValue(value = "user_id", defaultValue = "") String userid,
                           @CookieValue(value = "role", defaultValue = "") String role) {
        boolean logged = appCookies.checkLogged(model, userid, role);
        Shop shop = dbManager.getShopService().findById(id);
        if (shop == null)
            return "redirect:/";
        Merchant merchant = shop.getMerchant();
        List<Product> products = dbManager.getProductService().findAllByMerchant(merchant);
        if (products.size() != 0) {
            Date today = new Date();
            for (Product product : products) {
                for (Promotion promotion : product.getPromotion()) {
                    if (!isWithinRange(today, promotion.getStart(), promotion.getEnd())) {
                        product.getPromotion().remove(promotion);
                        dbManager.getProductService().saveProduct(product);
                    }
                }
            }
            model.addAttribute("products", products);
            model.addAttribute("shop", shop);
        }
        model.addAttribute("logged", logged);

        model.addAttribute("greeting", "Welcome to " + shop.getShopName());
        model.addAttribute("shopname", shop.getShopName());
        return "shop";
    }

    boolean isWithinRange(Date today, Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(today);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(startDate);
        int startDay = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(endDate);
        int endDay = cal.get(Calendar.DAY_OF_MONTH);
        if (day == startDay && day == endDay)
            return true;
        return !(today.before(startDate) || today.after(endDate));
    }
}
