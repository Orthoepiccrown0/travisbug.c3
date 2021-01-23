package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    void saveCategory(Category category);

    List<Category> getAll();

    Optional<Category> findById(Integer id);

}
