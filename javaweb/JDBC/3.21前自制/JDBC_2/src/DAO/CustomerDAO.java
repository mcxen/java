package DAO;
import bean.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/*
规范操作Customer的常用操作。
 */
public interface CustomerDAO {
//    将Cust对象添加到数据库。
    void insert(Connection conn, Customer cust);
    void deleteById(Connection conn,int id);
    void updateById(Connection conn,Customer cust);//针对cust对象，修改指定的记录
    Customer getCustomerById(Connection conn,int id);//查询记录
    List<Customer> getAll(Connection conn);//返回所有的对象
    Long getCount(Connection conn);//返回数据表中数据条目数
    Date getMaxBirth(Connection conn);
}
