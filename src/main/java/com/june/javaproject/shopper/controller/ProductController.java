package com.june.javaproject.shopper.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.june.javaproject.shopper.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public void createProduct() {
        productService.createProduct(1L, null);
        return;
    }

    @GetMapping("/")
    public void getAllProducts() {
        productService.getAllProducts();
        return;
    }

    @GetMapping("/{id}")
    public void getProductById(@PathVariable Long id) {
        productService.getProductById(id);
        return;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(1L, id);
        return;
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id) {
        productService.updateProduct(1L, null);
        return;
    }
}


