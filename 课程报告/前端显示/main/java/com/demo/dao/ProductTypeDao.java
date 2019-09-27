package com.demo.dao;

import com.demo.pojo.ProductType;

import java.util.List;

public interface ProductTypeDao {
    public List<ProductType> queryProductType(ProductType productType);
}
