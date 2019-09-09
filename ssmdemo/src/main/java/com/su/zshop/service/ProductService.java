package com.su.zshop.service;

import com.su.zshop.pojo.Product;
import com.su.zshop.pojo.ProductType;

import java.util.List;

public interface ProductService {
    public List<Product> queryProduct(Product product);
    public int insert(Product product);
    public int delete(Product producte);
    public int update(Product product);
}
