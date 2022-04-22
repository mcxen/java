package com.sqltest;

import util.JDBC_Utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

import static util.JDBC_Utils.getConnection;

public class Exercise2 {

    public static void main(String[] args) {
        Exercise2 ex2 = new Exercise2();
        ex2.delete();
        System.out.println("请问你要进行什么操作");
    }
    public void insertFunc(){
        //问题一 添加一个数据
        //数据获取
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入Type四六级 : ");
        int type = scanner.nextInt();
        System.out.print("输入idCard : ");
        String idCard = scanner.next();
        System.out.print("输入准考证号 : ");
        String examCard = scanner.next();
        System.out.print("学生姓名 : ");
        String studentName = scanner.next();
        System.out.print("所在城市 : ");
        String location = scanner.next();
        System.out.print("成绩 : ");
        int grade = scanner.nextInt();
        Exercise2 ex2 = new Exercise2();
        String sql= "insert into examstudent(Type,IDCard,ExamCard,StudentName,Location,Grade) values(?,?,?,?,?,?)";
        int insertCount = ex2.update(sql, type, idCard, examCard, studentName, location, grade);
        if (insertCount>0){
            System.out.println("添加成功");
        }
        else {
            System.out.println("添加失败");
        }
    }
    //问题二 查询
    public void query(){
        Exercise2 ex2 = new Exercise2();
        System.out.println("输入查询的类型，\n A 准考证号 \n B 身份证号");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.next();
        //if (selection.equals("a")) {
        if("a".equalsIgnoreCase(selection)){
            System.out.print("请输入准考证号码： ");
            String examID = scanner.next();
            String sql ="select FlowID flowID,Type type,IDCard IDCard,ExamCard examCard, StudentName name,Location location,Grade grade from examstudent where ExamCard = ?";
            Student instance = ex2.getInstance(Student.class, sql, examID);
            if (instance == null) {
                System.out.println("查无此人");
            }else {
                System.out.println(instance);
            }
        }else if ("b".equalsIgnoreCase(selection)){
            System.out.print("请输入身份证号码： ");
            String idCard = scanner.next();
            String sql ="select FlowID flowID,Type type,IDCard IDCard,ExamCard examCard, StudentName name,Location location,Grade grade from examstudent where IDCard = ?";
            Student instance = ex2.getInstance(Student.class, sql, idCard);
            if (instance == null) {
                System.out.println("查无此人");
            }else {
                System.out.println(instance);
            }
        }else {
            System.out.println("你的输入有误差");
        }

    }

    //问题三
    public void delete(){
        System.out.println("输入考号");
        Exercise2 ex2 = new Exercise2();
        Scanner scanner = new Scanner(System.in);
        String examID = scanner.next();
        String sql1="delete from examstudent where ExamCard = ?";
        int updateResult = ex2.update(sql1, examID);
        if (updateResult >0 ) {
            System.out.println("删除成功");
        }else {
            System.out.println("查无此人");
        }
    }

    /*
    public void delete(){
        System.out.println("输入考号");
        Exercise2 ex2 = new Exercise2();
        Scanner scanner = new Scanner(System.in);
        String examID = scanner.next();
        String sql ="select FlowID flowID,Type type,IDCard IDCard,ExamCard examCard, StudentName name,Location location,Grade grade from examstudent where ExamCard = ?";
        Student instance = ex2.getInstance(Student.class, sql, examID);
        if (instance == null) {
            System.out.println("查无此人");
        }else {
            String sql1="delete from examstudent where ExamCard = ?";
            ex2.update(sql1,examID);
            System.out.println("删除成功");
        }
    }
     */
    //通用增删改操作
    public int update(String sql, Object ...args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取link
            conn = getConnection();
            //预编译SQL语句
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //exe
            System.out.println("通用增删改操作完毕！");
//            ps.execute();//查询操作是true，不是查询，没有返回结果，就是说是增删改就是false
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBC_Utils.closeResources(conn,ps);
        }
        return 0;
    }

    public <T> T getInstance(Class<T> clazz,String sql,Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBC_Utils.getConnection();
            ps = conn.prepareStatement(sql);
            for(int i=0;i<args.length;i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                T t = clazz.newInstance();
                //Each COlume  and column name
                for (int i = 0; i < columnCount; i++) {
                    Object columValue=rs.getObject(i+1);
                    //获取列的别名，
                    String columnLabel =rsmd.getColumnLabel(i+1);
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columValue);
                }//经过循环之后就把t赋值了。
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_Utils.closeResources(conn,ps,rs);
        }

        return null;
    }


}
