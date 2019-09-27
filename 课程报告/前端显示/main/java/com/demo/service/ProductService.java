package com.demo.service;

import com.demo.dao.ProductDao;
import com.demo.pojo.Product;

import java.util.List;

public interface ProductService {
    public List<Product> queryProduct(Product product);
}
