package com.example.paginasSpring.repository;

import com.example.paginasSpring.model.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
            category.setId(resultSet.getInt("id"));
            category.setName(resultSet.getString("name"));
            return category;
        });

        logger.info("Se obtuvieron todas las categorías de la base de datos: " + categories);
        return categories;
    }
}