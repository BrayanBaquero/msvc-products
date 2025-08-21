package com.springcloud.msvc.products.controllers;


import com.springcloud.msvc.products.entities.Product;
import com.springcloud.msvc.products.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (id.equals(10L)){
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

    @PostMapping
    public  ResponseEntity<Product> create(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.save(product));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Product product){
        Optional<Product> productDb = productService.findById(id);
        if (productDb.isPresent()){
            Product productUpdate = productDb.orElseThrow();
            productUpdate.setName(product.getName());
            productUpdate.setPrice(product.getPrice());
            productUpdate.setCreatedAt(product.getCreatedAt());
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productUpdate));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id ){
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()){
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
