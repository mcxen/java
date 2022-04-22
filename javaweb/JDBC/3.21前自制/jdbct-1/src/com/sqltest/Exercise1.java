package com.sqltest;

import util.JDBC_Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import static util.JDBC_Utils.closeResources;
import static util.JDBC_Utils.getConnection;

public class Exercise1 {
    //TODO 测试
    public static void main(String[] args) throws Exception {

        Exercise1 ex = new Exercise1();
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入用户名");
        String name = scanner.next();
        System.out.println("输入邮箱");
        String email = scanner.next();
        System.out.println("输入生日");
        String birthday = scanner.next();

        String sql="insert into customers(id,name,email,birth)values (?,?,?,?)";
        int insertCount = ex.update(sql,28, name, email, birthday);
        if (insertCount>0) {
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }

    }

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
}
