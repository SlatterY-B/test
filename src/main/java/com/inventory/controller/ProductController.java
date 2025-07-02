package com.inventory.controller;

import com.inventory.entity.Product;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }



    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product) {
        return productService.save(product);
    }


    

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);

        if( product != null ) {
            return ResponseEntity.ok(product);

        }else {
            throw new ResourceNotFoundException("not found the id: " + id);

        }

    }


    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct( @PathVariable Long id, @RequestBody Product productReceived) {

        Product product = this.productService.findById(id);

        product.setDescription(productReceived.getDescription());
        product.setExistence(productReceived.getExistence());
        product.setPrice(productReceived.getPrice());

        this.productService.save(product);

        return ResponseEntity.ok(product);

    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {

        Product product = this.productService.findById(id);
        productService.delete(product);


    }



}
