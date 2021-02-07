package it.unicam.travisbug.c3;

import it.unicam.travisbug.c3.model.*;
import it.unicam.travisbug.c3.utils.DBManager;
import it.unicam.travisbug.c3.utils.PasswordTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
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
            addCouriers();
            addClients();
            addEmployee();
        }
    }

    private void addMerchant() {
        List<String> merchantInfos = new ArrayList<>(Arrays.asList("m@m", "n@n", "b@b", "v@v"));
        int count = 0;
        for (String minfo : merchantInfos) {
            Merchant merchant = new Merchant();
            merchant.setId(UUID.randomUUID().toString());
            merchant.setName(minfo);
            merchant.setSurname(minfo);
            merchant.setEmail(minfo);
            merchant.setPassword(PasswordTool.getMD5String(minfo.substring(0,1)));
            merchant.setShop(addShop(merchant, minfo, count));
            addProducts(merchant, minfo, count);
            dbManager.getMerchantService().saveMerchant(merchant);
            count++;
        }
    }

    private void addProducts(Merchant merchant, String minfo, int count) {
        List<Category> categories = dbManager.getCategoryService().getAll();
        for (int i = 0; i < 3; i++) {
            Product product = new Product();
            product.setCategory(categories.get(i));
            product.setName("Test " + i + count);
            product.setDescription("prodotto di " + minfo);
            product.setMerchant(merchant);
            product.setPrice(i + 1.0 + count);
            product.setSupply(5 * (i+1) + count);
            product.setWeight(1.0 + i + count);
            product.setPromoted(false);
            dbManager.getProductService().saveProduct(product);
        }
    }

    private Shop addShop(Merchant merchant, String minfo, int count) {
        List<ShopCategory> categories = dbManager.getShopCategoryService().getAll();
        Shop shop = new Shop();
        shop.setApproved(true);
        shop.setMerchant(merchant);
        shop.setShopName("Shop "+ minfo);
        shop.setShopCategory(categories.get(count));
        dbManager.getShopService().saveShop(shop);
        return shop;
    }

    private void addAddresses() {
        Address a = new Address();
        a.setCity("Send to Shop");
        a.setShipCharge(0.0);
        dbManager.getAddressService().saveAddress(a);

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

    private void addClients(){
        List<String> clientsInfo = new ArrayList<>(Arrays.asList("z@z", "x@x", "c@c"));
        for (String cinfo : clientsInfo) {
            Client client = new Client();
            client.setId(UUID.randomUUID().toString());
            client.setName(cinfo);
            client.setSurname(cinfo);
            client.setEmail(cinfo);
            client.setPassword(PasswordTool.getMD5String(cinfo.substring(0,1)));
            dbManager.getClientService().saveClient(client);
        }
    }

    private void addCouriers(){
        List<String> couriersInfo = new ArrayList<>(Arrays.asList("a@a", "s@s", "d@d", "f@f"));
        for (String cinfo : couriersInfo) {
            Courier courier = new Courier();
            courier.setId(UUID.randomUUID().toString());
            courier.setName(cinfo);
            courier.setSurname(cinfo);
            courier.setEmail(cinfo);
            courier.setPassword(PasswordTool.getMD5String(cinfo.substring(0,1)));
            dbManager.getCourierService().saveCourier(courier);
        }
    }

    private void addEmployee(){
        List<Shop> shops = dbManager.getShopService().getAll();
        List<String> employeeInfo = new ArrayList<>(Arrays.asList("g@g", "h@h", "j@j", "k@k"));
        int count = 0;
        for (String einfo : employeeInfo) {
            Employee employee = new Employee();
            employee.setId(UUID.randomUUID().toString());
            employee.setName(einfo);
            employee.setSurname(einfo);
            employee.setEmail(einfo);
            employee.setStatus("Approved");
            employee.setShop(shops.get(count));
            employee.setPassword(PasswordTool.getMD5String(einfo.substring(0,1)));
            dbManager.getEmployeeService().saveEmployee(employee);
            count++;
        }
    }

}
