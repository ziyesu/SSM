package com.su.zshop.dao;

import com.su.zshop.pojo.ProductType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ProductTypeDaoMapper {
    public List<ProductType> queryProduceType(ProductType productType);
    public int insert(ProductType productType);
    public int delete(ProductType productType);
    public int update(ProductType productType);
    //获取商品类别数据表的总记录条数
    public int queryProduceTypeCount(ProductType productType);

}
