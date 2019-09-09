package com.su.zshop.service.impl;

import com.su.zshop.dao.ProductDao;
import com.su.zshop.pojo.Product;
import com.su.zshop.pojo.ProductType;
import com.su.zshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductType> queryProduct(Product product) {

        //获取数据库表的总记录条数
        int totalCount=productDao.queryProduceCount(product);
        product.setTotalRecordSum(totalCount);


        return productDao.queryProduce(product);
    }



    @Override
    public int insert(Product product) {
        return productDao.insert(product);
    }

    @Override
    public int delete(Product product) {
        return productDao.delete(product);
    }

    @Override
    public int update(Product product) {

        System.out.println("update status");
        return productDao.update(product);
    }
}
