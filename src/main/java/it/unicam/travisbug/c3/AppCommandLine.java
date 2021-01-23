package it.unicam.travisbug.c3;

import it.unicam.travisbug.c3.model.Category;
import it.unicam.travisbug.c3.model.ShopCategory;
import it.unicam.travisbug.c3.utils.DBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppCommandLine {

    private DBManager dbManager;

    @Autowired
    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

//    public void run(String... args) {
//        Category c1 = new Category();
//        Category c2 = new Category();
//        Category c3 = new Category();
//        Category c4 = new Category();
//        Category c5 = new Category();
//        Category c6 = new Category();
//        Category c7 = new Category();
//        c1.setName("Videogame");
//        c2.setName("Food");
//        c3.setName("Film");
//        c4.setName("Flower");
//        c5.setName("Yewel");
//        c6.setName("Smartphone");
//        c7.setName("Watch");
//        c1.setId(12);
//        c1.setId(13);
//        c1.setId(14);
//        c1.setId(15);
//        c1.setId(16);
//        c1.setId(17);
//        c1.setId(18);
//        dbManager.getCategoryService().saveCategory(c1);
//        dbManager.getCategoryService().saveCategory(c2);
//        dbManager.getCategoryService().saveCategory(c3);
//        dbManager.getCategoryService().saveCategory(c4);
//        dbManager.getCategoryService().saveCategory(c5);
//        dbManager.getCategoryService().saveCategory(c6);
//        dbManager.getCategoryService().saveCategory(c7);
//        ShopCategory sc1 = new ShopCategory();
//        ShopCategory sc2 = new ShopCategory();
//        ShopCategory sc3 = new ShopCategory();
//        ShopCategory sc4 = new ShopCategory();
//        ShopCategory sc5 = new ShopCategory();
//        ShopCategory sc6 = new ShopCategory();
//        ShopCategory sc7 = new ShopCategory();
//        sc1.setNameCategory("Games");
//        sc2.setNameCategory("Restaurant");
//        sc3.setNameCategory("Electronics");
//        sc4.setNameCategory("Bakery");
//        sc5.setNameCategory("Yewelery");
//        sc6.setNameCategory("Clothes Shop");
//        sc7.setNameCategory("Shoe Shop");
//        dbManager.getShopCategoryService().saveShopCategory(sc1);
//        dbManager.getShopCategoryService().saveShopCategory(sc2);
//        dbManager.getShopCategoryService().saveShopCategory(sc3);
//        dbManager.getShopCategoryService().saveShopCategory(sc4);
//        dbManager.getShopCategoryService().saveShopCategory(sc5);
//        dbManager.getShopCategoryService().saveShopCategory(sc6);
//        dbManager.getShopCategoryService().saveShopCategory(sc7);
//    }

}
