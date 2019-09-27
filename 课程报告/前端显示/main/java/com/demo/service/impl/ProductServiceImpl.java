package com.demo.service.impl;

import com.demo.dao.ProductDao;
import com.demo.pojo.Product;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> queryProduct(Product product) {
        return productDao.queryProduct(product);
    }
}
