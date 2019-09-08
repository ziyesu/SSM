package com.gec.zshop.common;

/*
* 此类用于获取Connection对象
* */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    private static Connection connection=null;
    private final static String URL="jdbc:mysql://localhost:3306/zshop?useUnicde=true&characterEncoding=utf-8";
    private final static String USERNAME="root";
    private final static String PASSWD="";

    static
    {
        //初始化驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection openConn() throws SQLException {

        if(connection==null)
        {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWD);
        }

        return connection;
    }

    public static void closeConn()
    {
        if(connection!=null)
        {
            try {
                connection.close();
                connection=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
