package it.unicam.travisbug.c3.controller;

import it.unicam.travisbug.c3.model.Merchant;
import it.unicam.travisbug.c3.model.Shop;
import it.unicam.travisbug.c3.repository.ClientRepository;
import it.unicam.travisbug.c3.service.impl.MerchantServiceImpl;
import it.unicam.travisbug.c3.service.impl.ShopServiceImpl;
import it.unicam.travisbug.c3.utils.PasswordTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.UUID;

@Controller
public class MerchantRegistration {

    private MerchantServiceImpl merchantService;

    private ShopServiceImpl shopService;

    @Autowired
    public void setMerchantService(MerchantServiceImpl merchantService) {
        this.merchantService = merchantService;
    }

    @Autowired
    public void setShopService(ShopServiceImpl shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/register/merchant")
    public String merchantRegister(Model model) {
        return "merchantRegistration";
    }

    @PostMapping("/register/merchant")
    public String merchantRegister(Model model,
                                   String name,
                                   String surname,
                                   String email,
                                   String password,
                                   String phone,
                                   String shopName,
                                   String shopCategory,
                                   HttpServletResponse response) {
        Merchant merchant = new Merchant();
        merchant.setId(UUID.randomUUID().toString());
        merchant.setName(name);
        merchant.setSurname(surname);
        merchant.setPassword(PasswordTool.getMD5String(password));
        merchant.setEmail(email);
        if (phone != null)
            merchant.setPhone(phone);
        merchantService.saveMerchant(merchant);

        Shop shop = new Shop();
        shop.setMerchant(merchant);
        shop.setShopName(shopName);
        shopService.saveShop(shop);

        return "redirect:/";
    }

}
