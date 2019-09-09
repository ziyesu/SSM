package com.su.zshop.service.impl;

import com.su.zshop.dao.ProductTypeDaoMapper;
import com.su.zshop.pojo.ProductType;
import com.su.zshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDaoMapper productTypeDao;

    @Override
    public List<ProductType> queryProductType(ProductType productType) {

        //获取数据库表的总记录条数
        int totalCount=productTypeDao.queryProduceTypeCount(productType);
        productType.setTotalRecordSum(totalCount);


        return productTypeDao.queryProduceType(productType);
    }



    @Override
    public int insert(ProductType productType) {
        return productTypeDao.insert(productType);
    }

    @Override
    public int delete(ProductType productType) {
        return productTypeDao.delete(productType);
    }

    @Override
    public int update(ProductType productType) {
        if(productType.getStatus()==null || productType.getStatus()!=1){
            productType.setStatus(0);
        }
        System.out.println("update status");
        return productTypeDao.update(productType);
    }
}
