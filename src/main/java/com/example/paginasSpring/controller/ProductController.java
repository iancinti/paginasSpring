package com.example.paginasSpring.controller;

import com.example.paginasSpring.model.Product;
import com.example.paginasSpring.service.ProductService;
import com.example.paginasSpring.util.PageCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public PageCustom<Product> getAllProduct(@RequestParam int page, @RequestParam int items) {
        logger.info("Obteniendo productos");
        PageCustom<Product> productPage = service.getAllProduct(page, items);
        logger.info("Se obtuvieron los productos de la página: " + productPage);
        return productPage;
    }

    @PostMapping
    public void saveProduct(@RequestBody Product product) {
        logger.info("Guardando producto: " + product);
        service.saveProduct(product);
        logger.info("Producto guardado exitosamente: " + product);
    }

    @PatchMapping("/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody Product product) {
        logger.info("Actualizando producto ID: " + id + ": " + product);
        service.updateProduct(id, product);
        logger.info("Producto actualizado exitosamente ID: " + id + ": " + product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        logger.info("Eliminando producto ID: " + id);
        service.deleteProduct(id);
        logger.info("Producto eliminado exitosamente ID: " + id);
    }
}
