package com.springcloud.msvc.products.services.impl;

import com.springcloud.msvc.products.entities.Product;
import com.springcloud.msvc.products.repositories.ProductRpository;
import com.springcloud.msvc.products.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {


    final private ProductRpository productRpository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRpository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRpository.findById(id);
    }
}
