package it.unicam.travisbug.c3;

import it.unicam.travisbug.c3.model.*;
import it.unicam.travisbug.c3.utils.DBManager;
import it.unicam.travisbug.c3.utils.PasswordTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AppCommandLine implements CommandLineRunner {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void run(String... args) {
        List<Address> addresses = dbManager.getAddressService().getAll();
        if (addresses == null || addresses.size() == 0) {
            addCategories();
            addShopCategories();
            addAddresses();
            addMerchant();
        }
    }

    private void addMerchant() {
        Merchant merchant = new Merchant();
        merchant.setId(UUID.randomUUID().toString());
        merchant.setName("m");
        merchant.setSurname("m");
        merchant.setEmail("m@m");
        merchant.setPassword(PasswordTool.getMD5String("m"));

        merchant.setShop(addShop(merchant));
        addProducts(merchant);
        dbManager.getMerchantService().saveMerchant(merchant);
    }

    private void addProducts(Merchant merchant) {
        List<Category> categories = dbManager.getCategoryService().getAll();
        for (int i = 0; i < 2; i++) {
            Product product = new Product();
            product.setCategory(categories.get(i));
            product.setName("Test " + i);
            product.setDescription("Lorem ipsum");
            product.setMerchant(merchant);
            product.setPrice(i + 1.0);
            product.setSupply(5);
            product.setWeight(1.0);
            product.setPromoted(false);
            dbManager.getProductService().saveProduct(product);
        }
    }

    private Shop addShop(Merchant merchant) {
        List<ShopCategory> categories = dbManager.getShopCategoryService().getAll();
        Shop shop = new Shop();
        shop.setApproved(true);
        shop.setMerchant(merchant);
        shop.setShopName("Tottis");
        shop.setShopCategory(categories.get(0));
        dbManager.getShopService().saveShop(shop);
        return shop;
    }

    private void addAddresses() {
        String[] address_cities = {"Camerino"};
        String[] address_streets = {"Via Roma", "Via Napoli", "Via Milano", "Via Dante"};

        for (String city : address_cities) {
            double x = 0.0;
            for (String street : address_streets) {
                Address address = new Address();
                address.setCity(city);
                address.setStreet(street);
                address.setNumber(1);
                address.setShipCharge(x);
                x += 1;
                dbManager.getAddressService().saveAddress(address);
            }
        }
    }

    private void addShopCategories() {
        String[] categories = {"Games",
                "Restaurants",
                "Electronics",
                "Bakery",
                "Jewelery",
                "Clothes shop",
                "Shoe shop"};
        for (String cat : categories) {
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setNameCategory(cat);
            dbManager.getShopCategoryService().saveShopCategory(shopCategory);
        }
    }

    private void addCategories() {
        String[] categories = {"Video games",
                "Food",
                "Films",
                "Flowers",
                "Jewel",
                "Smartphone",
                "Watch"};
        for (String cat : categories) {
            Category category = new Category();
            category.setName(cat);
            dbManager.getCategoryService().saveCategory(category);
        }
    }

}
