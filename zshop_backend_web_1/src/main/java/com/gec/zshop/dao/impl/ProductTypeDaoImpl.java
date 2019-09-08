package com.gec.zshop.dao.impl;

import com.gec.zshop.common.DbUtils;
import com.gec.zshop.dao.ProductTypeDao;
import com.gec.zshop.pojo.ProductType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDaoImpl implements ProductTypeDao
{
    @Override
    public List<ProductType> queryProduceType(ProductType productType) {

        //创建集合对象
        List<ProductType> productTypeList=new ArrayList<>();

        //获取数据库连接对象
        Connection conn=null;
        try {
            conn=DbUtils.openConn();
            StringBuffer sqlBuf=new StringBuffer("SELECT * FROM t_product_type where 1=1");

            if(productType.getName()!=null && !productType.equals(""))
            {
                sqlBuf.append(" and name like '"+productType.getName()+"%'");
            }

            if(productType.getId()!=null && !productType.equals(""))
            {
                sqlBuf.append(" and id="+productType.getId());
            }

            //支持分页查询，添加区域查询实现
            sqlBuf.append(" limit "+productType.getStartLimitPos()+","+productType.getPageSize());


//            System.out.println(sqlBuf.toString());

            PreparedStatement pstmt=conn.prepareStatement(sqlBuf.toString());

            ResultSet rs=pstmt.executeQuery();

            while (rs.next())
            {
                int id=rs.getInt(1);
                String name=rs.getString(2);
                int status=rs.getInt(3);

                ProductType inProductType=new ProductType();
                inProductType.setId(id);
                inProductType.setName(name);
                inProductType.setStatus(status);

                productTypeList.add(inProductType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConn();
        }

        return productTypeList;
    }

    @Override
    public int insert(ProductType productType) {

        String insertSql="INSERT INTO t_product_type(NAME,STATUS) VALUES(?,?)";

        Connection connection=null;
        try {
            connection=DbUtils.openConn();

            PreparedStatement pstmt=connection.prepareStatement(insertSql);

            pstmt.setString(1,productType.getName());
            pstmt.setInt(2,productType.getStatus());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConn();
        }


        return 0;
    }

    @Override
    public int delete(ProductType productType) {

        String deleteSql="delete from t_product_type where id=?";

        Connection connection=null;
        try {
            connection=DbUtils.openConn();

            PreparedStatement pstmt=connection.prepareStatement(deleteSql);

            pstmt.setInt(1,productType.getId());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConn();
        }


        return 0;
    }

    @Override
    public int update(ProductType productType) {

        String updateSql="UPDATE t_product_type SET NAME=?,STATUS=? WHERE id=?;";

        Connection connection=null;
        try {
            connection=DbUtils.openConn();

            PreparedStatement pstmt=connection.prepareStatement(updateSql);

            pstmt.setString(1,productType.getName());
            pstmt.setInt(2,productType.getStatus());
            pstmt.setInt(3,productType.getId());

            int res = pstmt.executeUpdate();
            System.out.println(res);

            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConn();
        }

        return 0;
    }


    //查询数据表的总记录条数
    @Override
    public int queryProduceTypeCount(ProductType productType) {

        //获取数据库连接对象
        Connection conn=null;
        try {
            conn=DbUtils.openConn();
            StringBuffer sqlBuf=new StringBuffer("SELECT count(*) FROM t_product_type where 1=1");

            if(productType.getName()!=null && !productType.equals(""))
            {
                sqlBuf.append(" and name like '"+productType.getName()+"%'");
            }

            PreparedStatement pstmt=conn.prepareStatement(sqlBuf.toString());
            ResultSet rs=pstmt.executeQuery();

            while (rs.next())
            {
                int count=rs.getInt(1);
                return count;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DbUtils.closeConn();
        }

        return 0;
    }
}
