package com.su.zshop.service;

import com.su.zshop.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {

    public List<ProductType> queryProductType(ProductType productType);
    public int insert(ProductType productType);
    public int delete(ProductType productType);
    public int update(ProductType productType);

}
