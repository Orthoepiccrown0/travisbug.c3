package it.unicam.travisbug.c3.utils;

import it.unicam.travisbug.c3.model.shop.Category;

import java.util.Objects;

public class CategoryDetails implements Comparable<CategoryDetails> {

    private Category category;

    private int promotions;

    public CategoryDetails(Category category, int promotions) {
        this.category = category;
        this.promotions = promotions;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPromotions() {
        return promotions;
    }

    public void setPromotions(int promotions) {
        this.promotions = promotions;
    }

    public void incrementPromotion() {
        promotions += 1;
    }

    public String getName(){
        return category.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDetails that = (CategoryDetails) o;
        return category.getName().equals(that.getCategory().getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(category.getName());
    }

    @Override
    public int compareTo(CategoryDetails o) {
        return category.getName().compareTo(o.getCategory().getName());
    }
}
