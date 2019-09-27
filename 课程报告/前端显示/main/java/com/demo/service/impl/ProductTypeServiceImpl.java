package com.demo.service.impl;

import com.demo.dao.ProductTypeDao;
import com.demo.pojo.Product;
import com.demo.pojo.ProductType;
import com.demo.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    public List<ProductType> queryProductType(ProductType productType) {
        return productTypeDao.queryProductType(productType);
    }
}
