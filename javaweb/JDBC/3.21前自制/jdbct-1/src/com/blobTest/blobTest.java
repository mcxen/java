package com.blobTest;

import util.JDBC_Utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class blobTest {
    /*
    使用PreparedStatement操作Blob数据
     */
    public static void main(String[] args) throws Exception {
        blobTest blob = new blobTest();
        blob.insert();
    }
    public void insert() throws Exception {
        Connection conn = JDBC_Utils.getConnection();
        String sql = "insert into customers(name,email,birth,photo)values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1,"zhang");
        ps.setObject(2,"1@1.com");
        ps.setObject(3,"1900-01-11");
        FileInputStream is =new FileInputStream(new File("C:\\Users\\mcxen\\Downloads\\jdbct-1\\src\\com\\blobTest\\blob.jpg"));
        ps.setBlob(4,is);
        int updateResult = ps.executeUpdate();
        JDBC_Utils.closeResources(conn,ps);
    }
}
