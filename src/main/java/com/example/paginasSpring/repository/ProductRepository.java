package com.example.paginasSpring.repository;

import com.example.paginasSpring.model.Product;
import com.example.paginasSpring.util.PageCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private final Logger logger = LogManager.getLogger(ProductRepository.class);
    private final JdbcTemplate template;

    @Autowired
    public ProductRepository(JdbcTemplate template) {
        this.template = template;
    }


    public PageCustom<Product> getAllProducts(int page, int items) {
        int total = page * items;
        String query = "SELECT * FROM products LIMIT ? OFFSET ?";
        String countQuery = "SELECT COUNT(*) FROM products";
        logger.info("Ejecutando query para productos: " + query);

        List<Product> productList = template.query(query, this::mapToProduct, items, total);

        Integer count = template.queryForObject(countQuery, Integer.class);

        PageCustom<Product> productPagination = new PageCustom<>();
        productPagination.setItems(productList);
        productPagination.setTotal(count);

        logger.info("Se obtuvieron de la base los productos: " + productList);
        return productPagination;
    }


    public void saveProduct(Product product) {
        template.update(
                "INSERT INTO products (name, price) VALUES (?, ?)",
                product.getName(),
                product.getPrice()
        );
    }
    public void updateProduct(int id, Product product) {
        String query = "UPDATE products SET ";
        List<Object> params = new ArrayList<>();

        if (product.getName() != null) {
            query += "name = ?, ";
            params.add(product.getName());
        }

        if (product.getPrice() != null) {
            query += "price = ?, ";
            params.add(product.getPrice());
        }

        if (product.getDeletedAt() != null) {
            query += "deletedAt = ?, ";
            params.add(product.getDeletedAt());
        }

        if (!params.isEmpty()) {
            query = query.substring(0, query.length() - 2);
            query += " WHERE id = ?";
            params.add(id);

            logger.info("Ejecutando actualización de producto. Query: " + query + ", Parámetros: " + params);
            template.update(query, params.toArray());
            logger.info("Actualización de producto exitosa.");
        } else {
            logger.info("No hay parámetros de actualización, no se realizará ninguna acción.");
        }
    }



    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        template.update(sql, id);
    }

    private Product mapToProduct(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getInt("price"));
        return product;
    }
}
