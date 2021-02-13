package it.unicam.travisbug.c3.controller.personal.area;

import it.unicam.travisbug.c3.model.*;
import it.unicam.travisbug.c3.utils.AppCookies;
import it.unicam.travisbug.c3.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MerchantArea {

    private DBManager dbManager;

    private final AppCookies appCookies = AppCookies.getInstance();

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/myProductsArea")
    public String showMyProductArea(Model model,
                                    @CookieValue(value = "user_id", defaultValue = "") String userid,
                                    @CookieValue(value = "role", defaultValue = "") String role) {
        appCookies.checkLogged(model, userid, role, dbManager);

        Merchant m = dbManager.getMerchantService().findById(userid).orElseThrow();
        List<Product> products = dbManager.getProductService().findAllByMerchant(m);
        List<Category> categories = new ArrayList<>();
        for (Product product : products) {
            if (!categories.contains(product.getCategory()))
                categories.add(product.getCategory());
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        return "accounts/merchant/products/products";
    }

    @PostMapping("/myProductsArea")
    public String addDiscount(Model model,
                              Integer productDiscount,
                              Integer productId) {
        Product p = dbManager.getProductService().findById(productId);
        p.setDiscount(productDiscount);
        dbManager.getProductService().saveProduct(p);
        return "redirect:/myProductsArea";
    }

    @GetMapping("/myProductsArea/remove/{id}")
    public String removeProduct(Model model,
                                @PathVariable Integer id) {

        Product p = dbManager.getProductService().findById(id);
        dbManager.getProductService().deleteProduct(p);

        return "redirect:/myProductsArea";
    }

    @PostMapping("/addSupply")
    public String addSupply(Model model,
                            Integer supply,
                            Integer myProductId1) {
        Product p = dbManager.getProductService().findById(myProductId1);
        p.addSupply(supply);
        dbManager.getProductService().saveProduct(p);
        return "redirect:/myProductsArea";
    }

    @PostMapping("/addPromotion")
    public String createPromotion(Model model,
                                  @CookieValue(value = "user_id", defaultValue = "") String userid,
                                  Integer category_id, Integer discount, String startDate, String endDate) throws ParseException {
        Promotion promotion = new Promotion();
        promotion.setId(UUID.randomUUID().toString());
        Merchant merchant = dbManager.getMerchantService().findById(userid).orElseThrow();
        Set<Product> merchant_products = merchant.getProduct();
        promotion.setDiscount(discount);
        dbManager.getPromotionService().savePromotion(promotion);
        Set<Product> products = new HashSet<>();
        if (category_id == null)
            category_id = dbManager.getCategoryService().getAll().get(0).getId();
        Category category = dbManager.getCategoryService().findById(category_id).orElseThrow();
        for (Product product : category.getProduct()) {
            if (merchant_products.contains(product)) {
                product.addPromotion(promotion);
                dbManager.getProductService().saveProduct(product);
                products.add(product);
            }
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        promotion.setStart(format.parse(startDate));
        promotion.setEnd(format.parse(endDate));
        promotion.setProduct(products);
        dbManager.getPromotionService().savePromotion(promotion);

        AdminRequests request = new AdminRequests();
        request.setId(UUID.randomUUID().toString());
        request.setDate(new Date());
        request.setTitle("Promotion for " + merchant.getShop().getShopName());
        request.setComment("I want to discount "
                + category.getName()
                + " category by "
                + discount + "%");
        request.setPromotion(promotion);
        dbManager.getAdminRequestsService().saveAdminRequests(request);
        return "redirect:/myProductsArea";
    }

    @GetMapping("/addProduct")
    public String showAddProduct(Model model) {
        List<Category> categories = dbManager.getCategoryService().getAll();
        if (categories.size() != 0) {
            model.addAttribute("categories", categories);
        }
        return "accounts/merchant/products/add";
    }

    @PostMapping("/addProduct")
    public String insertProduct(Model model,
                                @CookieValue(value = "user_id", defaultValue = "") String userid,
                                String productName,
                                Double productPrice,
                                String productDescription,
                                Integer productSupply,
                                Double productWeight,
                                Integer productCategory) {
        Category c = dbManager.getCategoryService().findById(productCategory).orElseThrow();
        return addProduct(productName, userid, productPrice, productDescription, productSupply, productWeight, c);
    }

    private String addProduct(String productName,
                              String userid,
                              Double productPrice,
                              String productDescription,
                              Integer productSupply,
                              Double productWeight,
                              Category productCategory) {
        Product product = new Product();
        Merchant m = dbManager.getMerchantService().findById(userid).orElseThrow();
        product.setMerchant(m);
        product.setName(productName);
        product.setDescription(productDescription);
        product.setSupply(productSupply);
        product.setPrice(productPrice);
        product.setCategory(productCategory);
        if (productWeight != null) {
            product.setWeight(productWeight);
        }
        dbManager.getProductService().saveProduct(product);
        return "redirect:/myProductsArea";
    }

    @GetMapping("/notificationCentre")
    public String showNotificationCentre(Model model,
                                         @CookieValue(value = "user_id", defaultValue = "") String userid,
                                         @CookieValue(value = "role", defaultValue = "") String role) {
        appCookies.checkLogged(model, userid, role, dbManager);

        Merchant merchant = dbManager.getMerchantService().findById(userid).orElseThrow();
        List<EmployeeRequests> employeeRequests = dbManager.getEmployeeRequestsService().findAllByShopOrderByDateDesc(merchant.getShop());

        model.addAttribute("employeeRequests", employeeRequests);
        return "accounts/merchant/notifications";
    }

    @GetMapping("/notificationCentre/accept/{id}")
    public String accept(@PathVariable Integer id) {
        EmployeeRequests employeeRequests = dbManager.getEmployeeRequestsService().findById(id).orElseThrow();
        Employee employee = employeeRequests.getEmployee();
        employee.setStatus("Approved");

        dbManager.getEmployeeService().saveEmployee(employee);
        dbManager.getEmployeeRequestsService().deleteEmployeeRequest(employeeRequests);

        return "redirect:/notificationCentre";
    }

    @GetMapping("/notificationCentre/decline/{id}")
    public String decline(@PathVariable Integer id) {
        EmployeeRequests employeeRequests = dbManager.getEmployeeRequestsService().findById(id).orElseThrow();
        Employee employee = employeeRequests.getEmployee();
        employee.setStatus("Declined");

        dbManager.getEmployeeRequestsService().deleteEmployeeRequest(employeeRequests);
        dbManager.getEmployeeService().deleteEmployee(employee);

        return "redirect:/notificationCentre";
    }

    @GetMapping("/deleteShop")
    public String deleteShop(@CookieValue(value = "user_id", defaultValue = "") String userid) {
        Merchant m = dbManager.getMerchantService().findById(userid).orElseThrow();
        Shop s = m.getShop();
        dbManager.getShopService().deleteShop(s);
        dbManager.getMerchantService().deleteMerchant(m);
        return "redirect:/account/logout";
    }

}
