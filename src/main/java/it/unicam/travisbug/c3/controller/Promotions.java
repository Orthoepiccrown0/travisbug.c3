package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.shop.Category;
import it.unicam.travisbug.c3.model.shop.Product;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.CategoryDetails;
import it.unicam.travisbug.c3.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
public class Promotions {

    private final AppCookies appCookies = AppCookies.getInstance();
    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/promo/{category}")
    public String ShowPromotionsByCategory(Model model,
                                           @CookieValue(value = "user_id", defaultValue = "") String userid,
                                           @CookieValue(value = "role", defaultValue = "") String role,
                                           @PathVariable String category) {
        appCookies.checkLogged(model, userid, role, dbManager);
        Set<CategoryDetails> categoriesSet = findPromotedCategories();
        setCategoriesAndProducts(model, categoriesSet, category);
        return "promotions";
    }

    @GetMapping("/promo")
    public String showPromotions(Model model,
                                 @CookieValue(value = "user_id", defaultValue = "") String userid,
                                 @CookieValue(value = "role", defaultValue = "") String role) {
        appCookies.checkLogged(model, userid, role, dbManager);
        Set<CategoryDetails> categoriesSet = findPromotedCategories();
        setCategoriesAndProducts(model, categoriesSet);
        return "promotions";
    }

    private void setCategoriesAndProducts(Model model, Set<CategoryDetails> categoriesSet, String category) {
        List<CategoryDetails> categories = new ArrayList<>(categoriesSet);
        Collections.sort(categories);
        Category cat = null;
        for (CategoryDetails det : categories) {
            if (det.getName().equals(category))
                cat = det.getCategory();
        }
        if (cat != null) {
            List<Product> productsByCategory = dbManager.getProductService().findAllByCategory(cat);
            model.addAttribute("categories", categories);
            model.addAttribute("products", productsByCategory);
        }
    }

    private void setCategoriesAndProducts(Model model, Set<CategoryDetails> categoriesSet) {
        if (!categoriesSet.isEmpty()) {
            List<CategoryDetails> categories = new ArrayList<>(categoriesSet);
            Collections.sort(categories);
            List<Product> productsByCategory = filterNotAccepted(categories);
            model.addAttribute("categories", categories);
            model.addAttribute("products", productsByCategory);
        }
    }

    private List<Product> filterNotAccepted(List<CategoryDetails> categories) {
        Category category = categories.get(0).getCategory();
        List<Product> productsByCategory = dbManager.getProductService().findAllByCategory(category);
        productsByCategory.removeIf(product -> !product.promoted);
        if (productsByCategory.size() == 0) {
            categories.remove(0);
            for (int i = 0; i < categories.size(); i++) {
                category = categories.get(0).getCategory();
                productsByCategory = dbManager.getProductService().findAllByCategory(category);
                productsByCategory.removeIf(product -> !product.promoted);
                if (productsByCategory.size() != 0) {
                    break;
                } else {
                    categories.remove(0);
                }
            }
        }
        return productsByCategory;
    }

    private Set<CategoryDetails> findPromotedCategories() {
        List<Product> products = dbManager.getProductService().getAll();
        Set<CategoryDetails> categoriesSet = new HashSet<>();
        for (Product product : products) {
            if (product.promoted) {
                Category category = product.getCategory();
                CategoryDetails categoryDetails = new CategoryDetails(category, 1);
                CategoryDetails categoryPresent = findIfPresent(categoryDetails, categoriesSet);
                if (categoryPresent != null) {
                    categoryPresent.incrementPromotion();
                } else {
                    categoriesSet.add(categoryDetails);
                }
            }
        }
        return categoriesSet;
    }

    private CategoryDetails findIfPresent(CategoryDetails source, Set<CategoryDetails> set) {
        if (set.contains(source)) {
            for (CategoryDetails obj : set) {
                if (obj.equals(source))
                    return obj;
            }
        }
        return null;
    }
}
