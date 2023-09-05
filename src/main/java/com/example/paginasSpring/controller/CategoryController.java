package com.example.paginasSpring.controller;

import com.example.paginasSpring.model.Category;
import com.example.paginasSpring.service.CategoryService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final Logger logger = LogManager.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        logger.info("Obteniendo categorías");
        List<Category> categories = categoryService.getAllCategories();
        logger.info("Se obtuvieron las categorías: " + categories);
        return categories;
    }

    @PatchMapping("/{category}")
    public void updateCategory(@PathVariable int category, @RequestBody Category updatedCategory) {
        logger.info("Actualizando categoría con valor " + category + ": " + updatedCategory);
        categoryService.updateCategory(category, updatedCategory);
        logger.info("Categoría actualizada exitosamente con valor " + category + ": " + updatedCategory);
    }

}
