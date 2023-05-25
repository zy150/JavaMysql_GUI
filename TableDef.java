package learning.awt.Swing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TableDef {
    String name;
    double protein;
    double fat;
    double cs;
    int heat;
    double salt;
    int ca;
    private String insert;
    private String delete = "DELETE FROM table_test where name= ";
    private String update = "UPDATE table_test t SET ";
    private Statement statement;
    private String select;
    public TableDef(Statement statement){
        this.statement = statement;
    }
    public TableDef(String name, double protein, double fat, double cs, int heat, double salt, int ca, Statement statement) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.cs = cs;
        this.heat = heat;
        this.salt = salt;
        this.ca = ca;
        this.statement = statement;
    }
    //删除数据
    public boolean del(String s){
        boolean flag=false;
        delete+="'"+s+"'";
//        System.out.println(delete);
        if (s.equals(""))
            return false;
        try {
            statement.executeUpdate(delete);
            flag=true;
//            System.out.println("删除成功");
        }catch (Exception ee){
//            System.out.println("删除数据失败，请检查语法是否出错");
        }
        finally {
            return flag;
        }

    }
    //插入数据
    public boolean insert(String name,String protein,String fat,String cs,String heat,String salt,String ca) {
        boolean flag=false;
        try {
            insert = "INSERT INTO table_test(name,protein,fat,cs,heat,salt,ca)" + " VALUES("
                +"'"+name+"',"+protein+","+fat+","+cs+","+heat+","+salt+","+ca+")";
            statement.executeUpdate(insert);
            flag=true;
        }
        catch (Exception e){

        }finally {
            return flag;
        }

    }
    //更改数据
    public boolean upDate(String name,String pro,String fat,String cs,String heat,String salt,String ca){
        update+="t.protein="+pro+","+"t.fat="+fat+","+"t.cs="+cs+","+"t.heat="+heat+","+"t.salt="+salt+","+"t.ca="+ca+"where t.name="+"'"+name+"'";
        try {
            statement.executeUpdate(update);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }
    //查找数据
    public  String[] select(String name){
        String content []=new String[7];
        select="select * from table_test where name="+"'"+name+"'";
        try {
            //查询操作
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                content[0] = resultSet.getString("name");
                content[1] = resultSet.getString("id");
                content[2] = resultSet.getString("protein");
                content[3] = resultSet.getString("fat");
                content[4] = resultSet.getString("cs");
                content[5] = resultSet.getString("heat");
                content[6] = resultSet.getString("salt");
            }
            return content;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(0);
            return content;
        }

    }
}
