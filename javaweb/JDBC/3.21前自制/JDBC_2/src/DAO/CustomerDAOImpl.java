package DAO;

import bean.Customer;
import java.sql.Connection;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO implements CustomerDAO{

    public static void main(String[] args) {
    }
    @Override
    public void insert(Connection conn, Customer cust) {
        String sql ="insert into customers(name,email,birth)values(?,?,?);";
        update(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth());
    }

    @Override
    public void deleteById(Connection conn,int id) {
        String sql = "delete from customers where id =?";
        update(conn,sql,id);
    }


    @Override
    public void updateById(Connection conn, Customer cust) {
        String sql = "update customers set name = ?,email = ?,birth = ? where id = ?";
        update(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth(),cust.getId());
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        String sql="select id,name,email,birth from customers where id = ?";
        Customer cust=getInstance(conn,Customer.class,sql,id);
        return cust;
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        return null;
    }

//    @Override
//    public Long getCount(Connection conn) {
//        String sql ="select count(*) from customers";
//        return getValue(conn,sql);
//    }

    @Override
//    public Date getMaxBirth(Connection conn) {
//        String sql ="select max(birth) from customers";
//        return getValue(conn,sql);
//    }
//}
