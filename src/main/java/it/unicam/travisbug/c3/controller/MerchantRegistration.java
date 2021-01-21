package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.Shop;
import it.unicam.travisbug.c3.model.*;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import it.unicam.travisbug.c3.utils.PasswordTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Controller
public class MerchantRegistration {

    private DBManager dbManager;

    private final AppCookies appCookies = new AppCookies();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/register/merchant")
    public String merchantRegister(Model model) {
        return "accounts/merchantRegistration";
    }

    @PostMapping("/register/merchant")
    public String merchantRegister(Model model,
                                   String name,
                                   String surname,
                                   String email,
                                   String password,
                                   String phone,
                                   HttpServletResponse response,
                                   RedirectAttributes redirectAttrs) {
        if (!isUsedEmail(email)) {
            return registerMerchant(name, surname, email, password, phone, response, redirectAttrs);
//            return "redirect:/";
        }
        redirectAttrs.addAttribute("used_email", "used");
        return "redirect:/register/merchant";

    }

    private String registerMerchant(String name,
                                    String surname,
                                    String email,
                                    String password,
                                    String phone,
                                    HttpServletResponse response,
                                    RedirectAttributes redirectAttrs) {
        Merchant merchant = new Merchant();
        merchant.setId(UUID.randomUUID().toString());
        merchant.setName(name);
        merchant.setSurname(surname);
        merchant.setPassword(PasswordTool.getMD5String(password));
        merchant.setEmail(email);
        if (phone != null)
            merchant.setPhone(phone);
        dbManager.getMerchantService().saveMerchant(merchant);

        redirectAttrs.addAttribute("merchant", merchant);
        return "redirect:/register/merchant/shop";
    }

    @GetMapping("/register/merchant/shop")
    public String shopRegister(Model model, String merchant) {
        model.addAttribute("merchant", merchant);
        return "shopSubmit";
    }

    @PostMapping("/register/merchant/shop")
    public String saveShop(Model model,
                               String shopName,
                               String comment,
                               Merchant merchant,
                               HttpServletResponse response) {
        Shop shop = new Shop();
        shop.setMerchant(merchant);
        shop.setShopName(shopName);
        shop.setApproved(false);
        merchant.setShop(shop);
        dbManager.getShopService().saveShop(shop);
        dbManager.getMerchantService().saveMerchant(merchant);
        addRequest(shopName, comment, shop);

        appCookies.setUserIDCookie(merchant.getId(), response, true);
        appCookies.setRoleCookie("merchant", response);

        return "redirect:/";
    }

    private void addRequest(String shopName, String comment, Shop shop) {
        AdminRequests adminRequests = new AdminRequests();
        adminRequests.setId(UUID.randomUUID().toString());
        adminRequests.setShop(shop);
        adminRequests.setTitle(shopName);
        adminRequests.setComment(comment);
        adminRequests.setDate(new Date());
        dbManager.getAdminRequestsService().saveAdminRequests(adminRequests);
    }

    private boolean isUsedEmail(String email) {
        Client client = dbManager.getClientService().findByEmail(email);
        Courier courier = dbManager.getCourierService().findByEmail(email);
        Merchant merchant = dbManager.getMerchantService().findByEmail(email);
        return merchant != null || client !=null || courier!=null;
    }

}
