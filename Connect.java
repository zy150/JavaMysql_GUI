package learning.awt.Swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    static int cou =0;
    public static Statement connectStatament(){
        Statement sta=null;
        try {
            sta = connection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sta;
    }
    public  static Connection connection(){
        String DBURL = "jdbc:mysql://localhost:3306/bjpower";
        //jdbc:mysql: + ip + 库名
        String DBUSER = "root";
        //连接账户名
        String DBPASS = "123456789zyh";
        Connection con = null;
//        Statement sta=null;
        try {
            //连接数据库
            con = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
//            System.out.println("连接成功-当前用户-->"+DBUSER);
        }
        catch (Exception e){
            System.out.println("连接失败请重试");
        }
        return con;
    }
}
