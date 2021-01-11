package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Category;
import it.unicam.travisbug.c3.repository.CategoryRepository;
import it.unicam.travisbug.c3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Qualifier("categoryRepository")
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
