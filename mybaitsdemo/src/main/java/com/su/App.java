package com.su;


import com.su.pojo.ProductType;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {

//        System.out.println( "Hello World!" );
        //获取mybatis-config的全局xml文件的输入流对象

        InputStream inputStream= Resources.getResourceAsStream("mybatis-conf.xml");

        //获取SqlSessionFactory的对象
        SqlSessionFactory sf= new SqlSessionFactoryBuilder().build(inputStream);

        ProductType productType=new ProductType();
        productType.setName("ccs");
        productType.setStatus(1);
        //获取
        SqlSession session=sf.openSession();


//        session.insert(selectMapper("insertProductTpye"),productType);

        productType.setName("c");
        List<ProductType> productTypes=session.selectList(selectMapper("selectProductType"),productType);

        productTypes.forEach(p-> System.out.println(p));

        session.commit();
        session.close();

    }

    public static String selectMapper(String path){
        StringBuffer root=new StringBuffer("com.su.mapper.productTypeMapper.");
        return root.append(path).toString();
    }
}
