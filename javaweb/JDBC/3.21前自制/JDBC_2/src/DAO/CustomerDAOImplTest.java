package DAO;

import bean.Customer;
import util.JDBC_Utils;

import java.sql.Connection;
import java.sql.Date;


class CustomerDAOImplTest {

    public CustomerDAOImpl dao =  new CustomerDAOImpl();

    public static void main(String[] args) {
        CustomerDAOImplTest ctes = new CustomerDAOImplTest();
        ctes.insert();
    }
//    @org.junit.jupiter.api.Test
    void insert() {
        Connection conn = null;
        try {
            conn = JDBC_Utils.getConnection();
            Customer zhoujielun = new Customer(1, "zhoujielun", "shewhen@qq.com", new Date(1999 - 11 - 11));
            getDao().insert(conn, zhoujielun);
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_Utils.closeResources(conn, null);

        }
    }

    private CustomerDAOImpl getDao() {
        return dao;
    }

//    @org.junit.Test
    void deleteById() {
    }

//    @org.junit.est
    void updateBtId() {
    }

//    @org.junit.Test
    void getCount() {
    }
}