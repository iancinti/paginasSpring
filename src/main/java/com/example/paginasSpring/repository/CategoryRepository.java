package com.example.paginasSpring.repository;

import com.example.paginasSpring.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {

    private final Logger logger = LogManager.getLogger(CategoryRepository.class);
    private final JdbcTemplate template;

    @Autowired
    public CategoryRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<Category> getAll() {
        String query = "SELECT * FROM categories";
        logger.info("Ejecutando consulta para obtener todas las categorías: " + query);

        List<Category> categories = template.query(query, (resultSet, rowNum) -> {
            Category category = new Category();
            category.setCategory(resultSet.getInt("category"));
            category.setName(resultSet.getString("name"));
            return category;
        });

        logger.info("Se obtuvieron todas las categorías: " + categories);
        return categories;
    }

    public void updateCategory(int category, Category updatedCategory) {
        String query = "UPDATE categories SET ";
        List<Object> params = new ArrayList<>();

        if (updatedCategory.getName() != null) {
            query += "name = ?, ";
            params.add(updatedCategory.getName());
        }

        if (!params.isEmpty()) {
            query = query.substring(0, query.length() - 2);
            query += " WHERE category = ?";
            params.add(category);

            logger.info("Ejecutando actualización de categoría. Query: " + query + ", Parámetros: " + params);
            template.update(query, params.toArray());
            logger.info("Actualización de categoría exitosa.");
        } else {
            logger.info("No hay parámetros de actualización, no se realizará ninguna acción.");
        }
    }

}