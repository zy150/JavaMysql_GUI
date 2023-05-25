package learning.awt.Swing;

//import learning.Jdbc.Mysql.TableDef;
//import learning.Jdbc.Mysql.Test01;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Statement;

public class mainGUI extends JFrame {
    boolean flag=false;
    public mainGUI(){
        this.init();
    }
    public void setOpenAndClose(boolean f){
        this.setVisible(f);
//        flag=true;
    }
    public void init() {
        this.setSize(600,500);
        //设置居中 获取容器 设置自由摆放组件
        this.setLocationRelativeTo(null);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        //设置标题 及提示内容
        Font fontTitle = new Font("宋体",Font.TYPE1_FONT,32);
        Font fontSelect = new Font("宋体",Font.BOLD,18);
        JLabel title = new JLabel("食物营养成分表");
        JLabel select = new JLabel("请输入食物名称");
        title.setFont(fontTitle);
        select.setFont(fontSelect);
        title.setBounds(170,10,250,50);
        select.setBounds(100,70,150,50);
        contentPane.add(title);
        contentPane.add(select);

        //设置 食物名称框
        TextField textName = new TextField();
        textName.setBounds(250,85,150,25);
        contentPane.add(textName);

        //按钮
        JButton selectName = new JButton("查询");
        JButton add = new JButton("添加");
        JButton change = new JButton("修改");
        JButton del = new JButton("删除");
        selectName.setBounds(420,80,60,30);
        add.setBounds(150,300,80,50);
        change.setBounds(250,300,80,50);
        del.setBounds(350,300,80,50);

        contentPane.add(selectName);
        contentPane.add(add);
        contentPane.add(change);
        contentPane.add(del);
        //内容列表
        String tableTitle[] = {"食物名称","蛋白质","脂肪","碳水化合物","热量","无机盐类","钙"};
        String tableContent[][]= {{"食物名称","蛋白质","脂肪","碳水化合物","热量","无机盐类","钙"},{"豆腐浆","1.6","0.7","1","17","0.2","-"},{"猪油","-","99","-","391","-","-"}};
        JTable table = new JTable(tableContent,tableTitle);
        table.setEnabled(false);
        table.setBounds(50, 150, 500, 100);
        contentPane.add(table);

        //查询操作
        selectName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //弹框居中
                JDialog landStatus = new JDialog();
                landStatus.setVisible(true);
                Container contentPane1 = landStatus.getContentPane();
                landStatus.setLocationRelativeTo(null);
                landStatus.setSize(600,100);

                //获取 名称
                String name = textName.getText();
                String content[] = new TableDef(Connect.connectStatament()).select(name);
                //查询返回结果放入二位数组
                String selectResult[][]= {{"食物名称","蛋白质","脂肪","碳水化合物","热量","无机盐类","钙"},{"食物名称","蛋白质","脂肪","碳水化合物","热量","无机盐类","钙"}};
                for (int i=0;i<7;i++){
                    selectResult[1][i]=content[i];
                }
                //显示结果
                if (selectResult[1][0]==null){
                    contentPane1.add(new JLabel("请输入合法名称",SwingConstants.CENTER));
                }
                else{
                    JTable cont = new JTable(selectResult, tableTitle);
                    cont.setSize(100,20);
                    cont.setEnabled(false);
                    contentPane1.add(cont);
                }

            }
        });

        //添加操作
        add.addActionListener(new AbstractAction() {
            //弹出多个框 输入数据后 进行添加 名字 不允许重复不允许填null 其他可为空 确认后调sql函数
            public void actionPerformed(ActionEvent e) {
                JDialog addText = new JDialog();
                addText.setSize(600,450);
                addText.setLayout(null);
                addText.setLocationRelativeTo(null);

                //设置字体
                Font font = new Font("宋体",Font.BOLD,20);

                //设置7个内容框
                JTextField c1 = new JTextField();
                JTextField c2 = new JTextField();
                JTextField c3 = new JTextField();
                JTextField c4 = new JTextField();
                JTextField c5 = new JTextField();
                JTextField c6 = new JTextField();
                JTextField c7 = new JTextField();

                c1.setBounds(280,30,150,30);
                c2.setBounds(280,70,150,30);
                c3.setBounds(280,110,150,30);
                c4.setBounds(280,150,150,30);
                c5.setBounds(280,190,150,30);
                c6.setBounds(280,230,150,30);
                c7.setBounds(280,270,150,30);

//                c1.setBounds();

                //设置 提交按钮
                JButton submitAdd = new JButton("提交");
                submitAdd.setBounds(250,350,70,50);
                submitAdd.addActionListener(new AbstractAction() {
                    //提交数据
                    public void actionPerformed(ActionEvent e) {
                        String name = c1.getText();
                        String protein = c2.getText();
                        String fat = c3.getText();
                        String cs = c4.getText();
                        String heat = c5.getText();
                        String salt = c6.getText();
                        String ca = c7.getText();

                        boolean f = new TableDef(Connect.connectStatament()).insert(name,protein,fat,cs,heat,salt,ca);

                        //弹框结果
                        JDialog insertResult = new JDialog();
                        insertResult.setSize(300, 150);
                        insertResult.setLocationRelativeTo(null);

                        if (f){
                            insertResult.add(new JLabel("添加成功",SwingConstants.CENTER));
                        }
                        else {
                            insertResult.add(new JLabel("添加失败,请检查数据是否合法",SwingConstants.CENTER));
                        }
                        insertResult.setVisible(true);
                    }
                });

                //设置7个Label
                JLabel l1 = new JLabel("名称");
                JLabel l2 = new JLabel("蛋白质");
                JLabel l3 = new JLabel("脂肪");
                JLabel l4 = new JLabel("碳水化合物");
                JLabel l5 = new JLabel("热量");
                JLabel l6 = new JLabel("无机盐类");
                JLabel l7 = new JLabel("钙");

                l1.setFont(font);
                l2.setFont(font);
                l3.setFont(font);
                l4.setFont(font);
                l5.setFont(font);
                l6.setFont(font);
                l7.setFont(font);

                l1.setBounds(150,30,100,30);
                l2.setBounds(150,70,100,30);
                l3.setBounds(150,110,100,30);
                l4.setBounds(150,150,150,30);
                l5.setBounds(150,190,100,30);
                l6.setBounds(150,230,100,30);
                l7.setBounds(150,270,100,30);

                addText.add(l1);
                addText.add(l2);
                addText.add(l3);
                addText.add(l4);
                addText.add(l5);
                addText.add(l6);
                addText.add(l7);

                addText.add(c1);
                addText.add(c2);
                addText.add(c3);
                addText.add(c4);
                addText.add(c5);
                addText.add(c6);
                addText.add(c7);

                addText.add(submitAdd);

                addText.setVisible(true);
            }
        });

        //修改操作
        change.addActionListener(new AbstractAction() {
            //先输入名称 然后弹出表单 修改表单的值 检查用户数据是否合法
            public void actionPerformed(ActionEvent e) {
                JDialog changejd = new JDialog();
                changejd.setSize(600,200);
                changejd.setLayout(null);
                Font changeFont = new Font("宋体", Font.BOLD, 15);

                //提示文本
                JLabel changeLabel = new JLabel("请输入想要查询的名称");
                changeLabel.setFont(changeFont);
                changeLabel.setBounds(150,30,200,50);

                //名称输入框
                JTextField changeText = new JTextField();
                changeText.setBounds(320,40,150,30);

                //按钮
                JButton changeButton = new JButton("确定");
                changeButton.setBounds(250, 80, 70, 40);
                changeButton.addActionListener(new AbstractAction() {
                    //点击确定后 返回表单数据
                    public void actionPerformed(ActionEvent e) {
                        //关闭原窗口 创建新窗口 隐藏主窗口
//                        changejd.setVisible(false);
//                        setOpenAndClose(false);
                        JDialog returnContent = new JDialog();
                        returnContent.setLayout(null);
                        returnContent.setSize(600, 200);

                        JDialog returnJdialog = new JDialog();
                        //获取 名称
                        String name = changeText.getText();
                        String content[] = new TableDef(Connect.connectStatament()).select(name);
                        //查询返回结果放入二位数组
                        String returnTitle [] = {"食物名称","蛋白质","脂肪","碳水化合物","热量","无机盐类","钙"};
                        String selectResult[][]= {{"食物名称","蛋白质","脂肪","碳水化合物","热量","无机盐类","钙"},{"食物名称","蛋白质","脂肪","碳水化合物","热量","无机盐类","钙"}};
                        for (int i=0;i<7;i++){
                            selectResult[1][i]=content[i];
                        }
                        //显示结果
                        if (selectResult[1][0]==null){
                            returnContent.add(new JLabel("请输入合法名称",SwingConstants.CENTER));
                            changejd.setVisible(true);
                        }
                        else{
                            Font tableFont = new Font("宋体", Font.TRUETYPE_FONT,15);
                            JTable cont = new JTable(selectResult, returnTitle);
                            cont.setFont(tableFont);
                            cont.setSize(600,80);
                            //提交按钮
                            JButton sumbit = new JButton("提交");
                            sumbit.setBounds(250,90,80,50);

                            //表单事件监听
//                            cont.getModel().addTableModelListener(new TableModelListener() {
//                                //获取其更改内容
//                                public void tableChanged(TableModelEvent e) {
//                                    DefaultTableModel tableModel = (DefaultTableModel) cont.getModel();
//                                    String cellData=(String) tableModel.getValueAt(0,0);
//                                    System.out.println(cellData);
//                                }
//                            });

                            //提交事件监听
                            sumbit.addActionListener(new AbstractAction() {
                                //提交 调用mysql函数 进行表单监听
                                public void actionPerformed(ActionEvent e) {
//                                    System.out.println(cont.getValueAt(1,0));
                                    //获取表单内容
                                    String name =(String) cont.getValueAt(1,0);
                                    String pro =(String) cont.getValueAt(1,1);
                                    String fat =(String) cont.getValueAt(1,2);
                                    String cs =(String) cont.getValueAt(1,3);
                                    String heat =(String) cont.getValueAt(1,4);
                                    String salt =(String) cont.getValueAt(1,5);
                                    String ca =(String) cont.getValueAt(1,6);
                                    boolean f = new TableDef(Connect.connectStatament()).upDate(name,pro,fat,cs,heat,salt,ca);

                                    //修改状况  弹窗
                                    JDialog confirm = new JDialog();
                                    confirm.setSize(200,100);
                                    confirm.setLocationRelativeTo(null);
                                    if (f){
                                        confirm.add(new JLabel("修改成功",SwingConstants.CENTER));
                                    }
                                    else {
                                        confirm.add(new JLabel("修改失败,请检查数据是否合法",SwingConstants.CENTER));
                                    } confirm.setVisible(true);
                                }
                            });

                            returnContent.add(sumbit);
//                            cont.setFont();
                            returnContent.add(cont);
                        }
                        returnContent.setVisible(true);
                        returnContent.setLocationRelativeTo(null);
                    }
                });

                changejd.add(changeButton);
                changejd.add(changeText);
                changejd.add(changeLabel);

                changejd.setVisible(true);
                changejd.setLocationRelativeTo(null);

            }
        });

        //删除操作
        del.addActionListener(new AbstractAction() {
            //先弹出框 输入名字 然后确认

            public void actionPerformed(ActionEvent e) {
                JDialog delete = new JDialog();
                delete.setSize(500,250);
                delete.setLocationRelativeTo(null);
                delete.setLayout(null);

                Font delFont = new Font("宋体",Font.BOLD,18

                );

                //提示文本
                JLabel delTip = new JLabel("请输入想删除的名称");
                delTip.setBounds(60,30,200,50);
                delTip.setFont(delFont);

                //输入框
                JTextField delText = new JTextField();
                delText.setBounds(250,40,150,30);

                //提交按钮
                JButton delSubmit = new JButton("确认");
                delSubmit.setBounds(220,120,80,50);

                delSubmit.addActionListener(new AbstractAction() {
                    //提交信息
                    public void actionPerformed(ActionEvent e) {

                        boolean f = new TableDef(Connect.connectStatament()).del(delText.getText());

                        //设置删除弹框
                        JDialog delResult = new JDialog();
                        delResult.setSize(400,300);
                        delResult.setVisible(true);
                        delResult.setLocationRelativeTo(null);
                        JLabel t;
                        if (f){
                            t=new JLabel("删除成功",SwingConstants.CENTER);
                            t.setFont(delFont);
                        }
                        else {
                            t = new JLabel("删除失败,请检查名称是否正确",SwingConstants.CENTER);
                            t.setFont(delFont);
                        }
                        delResult.add(t);
                    }
                });

                delete.add(delTip);
                delete.add(delText);
                delete.add(delSubmit);

                delete.setVisible(true);

            }
        });




        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
