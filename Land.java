package learning.awt.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Land extends JFrame {
    List<String> userName = new ArrayList<String>();
    List<String> userPassword = new ArrayList<String>();



    public void init() {
        //添加用户
        this.addUser("root","root");
        this.setSize(600,500);

        //设置居中
        this.setLocationRelativeTo(null);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        Point center = this.getLocation();//获取中心位置坐标
        Font font = new Font("宋体",Font.BOLD,25);

        //设置用户名和密码
        JLabel jLabel1= new JLabel("用户名");
        JLabel jLabel2= new JLabel("密 码");
        jLabel1.setFont(font);
        jLabel2.setFont(font);
        jLabel1.setBounds(170,100,80,50);
        jLabel2.setBounds(170,180,80,50);
        contentPane.add(jLabel1);
        contentPane.add(jLabel2);

        //设置输入框
        JTextField jTextField1 = new JTextField();
        JPasswordField jTextField2 = new JPasswordField();

        jTextField1.setBounds(260,110,150,30);
        jTextField2.setBounds(260,190,150,30);

        //设置登陆按钮
        JButton jButton1 = new JButton("重置");
        JButton jButton2 = new JButton("登陆");
        jButton1.setBounds(260,260,60,40);
        jButton2.setBounds(350,260,60,40);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextField1.setText("");
                jTextField2.setText("");
            }
        });
        jButton2.addActionListener(new ActionListener() {
            //登陆情况
            public void actionPerformed(ActionEvent e) {
                //登陆验证
                boolean flag = false;
                JDialog landStatus = new JDialog();
                landStatus.setVisible(true);
                Container contentPane1 = landStatus.getContentPane();
                landStatus.setLocationRelativeTo(null);
                landStatus.setSize(300, 200);
                landStatus.setLayout(null);
                //确认登陆按钮 错误提示框
                JLabel tip;
                JButton bu;

                //获取name password
                String username = jTextField1.getText();
                String userpassword = jTextField2.getText();

                if (userName.contains(username) && userPassword.contains(userpassword))
                    flag=true;
                if (flag) {
                    bu = new JButton("登陆成功");
                    bu.setBounds(100,50,100,60);
                    landStatus.add(bu);
                    bu.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
//                           System.out.println("1");
                            //连接数据库
                            Connect.connection();
                            //创建主页面
                            Main.makeMain();
                            //隐藏之前的登陆界面
                            dispose();
                            landStatus.dispose();
                        }
                    });
                } else {
                    tip = new JLabel("登陆失败,用户名或密码不正确", SwingConstants.CENTER);
//                    tip = new JLabel("小周,你终于写完了可离散电磁高数怎么办呢", SwingConstants.CENTER);
                    Font f1 = new Font("宋体",Font.BOLD,18);
                    tip.setFont(f1);
                    tip.setBounds(20,50,400,50);
                    contentPane1.add(tip);
                }
            }
        });

        contentPane.add(jButton1);
        contentPane.add(jButton2);
        contentPane.add(jTextField1);
        contentPane.add(jTextField2);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
    public Land() {
        this.init();
    }
    public void addUser(String name,String pass){
        this.userName.add(name);
        this.userPassword.add(pass);
    }
}
