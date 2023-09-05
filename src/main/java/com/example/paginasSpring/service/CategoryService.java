package com.example.paginasSpring.service;

import com.example.paginasSpring.model.Category;
import com.example.paginasSpring.repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final Logger logger = LogManager.getLogger(CategoryService.class);

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllCategories() {
        logger.info("Obteniendo categorías");
        List<Category> categories = repository.getAll();
        logger.info("Se obtuvieron las categorías exitosamente: " + categories);
        return categories;
    }

}
