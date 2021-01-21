package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.model.Product;
import it.unicam.travisbug.c3.model.Shop;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MerchantArea {

    private DBManager dbManager;

    private final AppCookies appCookies = new AppCookies();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/myProductsArea")
    public String showMyProductArea(Model model,
                                   @CookieValue(value = "user_id", defaultValue = "") String userid,
                                   @CookieValue(value = "role", defaultValue = "") String role){
        appCookies.checkLogged(model, userid, role);
        List<Product> products = dbManager.getProductService().getAll();
        if (products.size() != 0) {
            model.addAttribute("products", products);
        }
        return "myProductsArea";
    }

    @GetMapping("/addProduct")
    public String showAddProduct(Model model){
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String insertProduct(Model model,
                                @CookieValue(value = "user_id", defaultValue = "") String userid,
                                String productName,
                                Double productPrice,
                                String productDescription,
                                Integer productSupply,
                                Double productWeight
                                //Category productCategory)
                                ){
        return addProduct(productName,userid,productPrice,productDescription,productSupply,productWeight);
    }

    private String addProduct(String productName,
                              String userid,
                              Double productPrice,
                              String productDescription,
                              Integer productSupply,
                              Double productWeight
                              //Category productCategory)
                              ){
        Product product = new Product();
        Merchant m = dbManager.getMerchantService().findById(userid).orElseThrow();
        product.setMerchant(m);
        product.setName(productName);
        product.setDescription(productDescription);
        product.setSupply(productSupply);
        product.setPrice(productPrice);
        //product.setCategory(productCategory);
        if (productWeight != null) {
            product.setWeight(productWeight);
        }
        dbManager.getProductService().saveProduct(product);
        return "redirect:/myProductsArea";
    }

    @GetMapping("/deleteShop")
    public String deleteShop(@CookieValue(value = "user_id", defaultValue = "") String userid){
        Merchant m = dbManager.getMerchantService().findById(userid).orElseThrow();
        Shop s = m.getShop();
        dbManager.getShopService().deleteShop(s);
        dbManager.getMerchantService().deleteMerchant(m);
        return "redirect:/user_logout";
    }

}
