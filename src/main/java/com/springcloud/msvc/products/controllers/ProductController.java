package com.springcloud.msvc.products.controllers;


import com.springcloud.msvc.products.entities.Product;
import com.springcloud.msvc.products.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
@RequestMapping
public class ProductController {

     private final ProductService productService;

    @GetMapping
    public List<Product> list(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> details(@PathVariable Long id) throws InterruptedException {
        Optional<Product> product = productService.findById(id);
        if (id.equals(10)){
            throw new IllegalStateException("Producto no encontrado");
        }
        if (id.equals(7L)){
            TimeUnit.SECONDS.sleep(3L);
           // throw new IllegalStateException("Producto no encontrado");
        }

        if (product.isPresent()){
            return ResponseEntity.ok(product.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
