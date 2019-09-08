package com.gec.zshop.dao;

import com.gec.zshop.dao.impl.ProductTypeDaoImpl;

/*
* 产生Dao的工厂类
* */
public class DaoFactory {

    public static ProductTypeDao createProductTypeDao()
    {
        return new ProductTypeDaoImpl();
    }
}
