package learning.awt.Swing;

import java.sql.Connection;
import java.sql.Statement;

public class Main {
     Connection con = null;
     Statement sta=null;
    public static void main(String[] args) {
        new Land();
    }
    //登陆成功 跳转主页面
    public static void makeMain(){
        //创建主页面
        new mainGUI();
    }
    public Main(){
//        this.connection();
    }
}
