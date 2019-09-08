package com.gec.zshop.service.impl;

import com.gec.zshop.dao.DaoFactory;
import com.gec.zshop.dao.ProductTypeDao;
import com.gec.zshop.pojo.ProductType;
import com.gec.zshop.service.ProductTypeService;

import java.util.List;

public class ProductTypeServiceImpl implements ProductTypeService
{
    private ProductTypeDao productTypeDao;

    public ProductTypeServiceImpl() {

        productTypeDao= DaoFactory.createProductTypeDao();

    }

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
