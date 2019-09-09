package com.su.zshop;

import com.su.zshop.common.util;
import com.su.zshop.pojo.ProductType;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class AddProduct {
    public static void main(String[] args) throws IOException {

        InputStream inputStream= Resources.getResourceAsStream("mybatis-conf.xml");
        //获取SqlSessionFactory的对象
        SqlSessionFactory sf= new SqlSessionFactoryBuilder().build(inputStream);


        ProductType productType= new ProductType();
        productType.setName("dfcccc");
        productType.setStatus(0);



        //获取
        SqlSession session=sf.openSession();

        System.out.println(productType.getId());

        session.insert(util.selectMapper("productTypeMapper.insertProductTpye"),productType);

        System.out.println(productType.getId());

//        Product product= new Product();
//        product.setImage("1232132132312");
//        product.setInfo("info");
//        product.setName("zy");
//        product.setPrice(123.34);
//        product.setProductType(productType);
//
//
//        session.insert(util.selectMapper("productMapper.insertProduct"),productType);


    }


}
