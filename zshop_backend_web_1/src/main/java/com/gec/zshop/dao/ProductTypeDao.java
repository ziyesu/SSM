package com.gec.zshop.dao;

import com.gec.zshop.pojo.ProductType;

import java.util.List;

public interface ProductTypeDao {

    public List<ProductType> queryProduceType(ProductType productType);
    public int insert(ProductType productType);
    public int delete(ProductType productType);
    public int update(ProductType productType);
    //获取商品类别数据表的总记录条数
    public int queryProduceTypeCount(ProductType productType);


}
