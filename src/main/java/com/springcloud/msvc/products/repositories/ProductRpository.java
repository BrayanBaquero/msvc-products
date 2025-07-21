package com.springcloud.msvc.products.repositories;

import com.springcloud.msvc.products.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRpository extends CrudRepository<Product,Long> {
}
