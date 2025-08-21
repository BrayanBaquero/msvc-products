package com.springcloud.msvc.products.services.impl;

import com.springcloud.msvc.products.entities.Product;
import com.springcloud.msvc.products.repositories.ProductRepository;
import com.springcloud.msvc.products.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {


    final private ProductRepository productRpository;
    final private Environment environment;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return ((List<Product>) productRpository.findAll()).stream()
                    .map(product -> {
                        product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                        return product;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRpository.findById(id).map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        });
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRpository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRpository.deleteById(id);
    }
}
