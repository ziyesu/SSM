package com.su.zshop.dao;

import com.su.zshop.pojo.Product;
import com.su.zshop.pojo.ProductType;

import java.util.List;

public interface ProductDao {
    public List<Product> queryProduce(Product product);
    public int insert(Product product);
    public int delete(Product product);
    public int update(Product product);
//    //获取商品类别数据表的总记录条数
    public int queryProduceCount(Product product);
}
