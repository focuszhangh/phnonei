package cn.fozhcus;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class test {
    private static String url;
    private static String user;
    private static String passwd;
    static{
        //加载配置文件
        Properties pro=new Properties();
        try {
            //加载配置文件
            pro.load(test.class.getClassLoader().getResourceAsStream("phoenix.properties"));
            //加载驱动
            Class.forName(pro.getProperty("phoenix.driver"));


        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取链接要用的参数
        url=pro.getProperty("phoenix.url");
        user=pro.getProperty("phoenix.user");
        passwd=pro.getProperty("phoenix.password");



    }
    public static void main(String[] args) {
        try {
            //获取链接
            Connection con=DriverManager.getConnection(url,user,passwd);
            String sql = "select * from BASEINFO where age=?";
            //preparestatement 比createstatement要快，它主要是通过？占位符来给定参数（也就是插入的数据）
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1,"5");
            //执行
            ResultSet set=ps.executeQuery();
            while(set.next())
            {
                System.out.println(set.getString("gender"));
                System.out.println(set.getString("name"));
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
