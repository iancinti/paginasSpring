package com.example.paginasSpring.service;

import com.example.paginasSpring.model.Product;
import com.example.paginasSpring.repository.ProductRepository;
import com.example.paginasSpring.util.PageCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final Logger logger = LogManager.getLogger(ProductService.class);

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public PageCustom<Product> getAllProduct(int page, int items) {
        logger.info("Calculando productos");
        PageCustom<Product> products = repository.getAllProducts(page, items);
        logger.info("Se calcularon los productos exitosamente: " + products);
        return products;
    }

    public void saveProduct(Product product) {
        logger.info("Guardando producto: " + product);
        repository.saveProduct(product);
        logger.info("Producto guardado exitosamente: " + product);
    }

    public void updateProduct(int id, Product product) {
        logger.info("Actualizando producto ID: " + id + ": " + product);
        repository.updateProduct(id, product);
        logger.info("Producto actualizado exitosamente: " + product);
    }

    public void deleteProduct(int id) {
        logger.info("Eliminando producto ID: " + id);
        repository.deleteProduct(id);
        logger.info("Producto eliminado exitosamente ID: " + id);
    }
}
