package com.inventory.service;

import com.inventory.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll();

    Product findById (Long id);

    Product save(Product product);

    void delete(Product product);

}
