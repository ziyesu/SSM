package com.demo.dao;

import com.demo.pojo.Product;

import java.util.List;

public interface ProductDao {

    public List<Product> queryProduct(Product product);

}
