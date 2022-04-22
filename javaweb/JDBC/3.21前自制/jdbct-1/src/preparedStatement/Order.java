package preparedStatement;

import java.sql.Date;

public class Order {
    private int orderId;
    private String orderName;
    private Date orderDate; //Date 导入的是sql下的。

    public Order(int orderId, String orderName, Date orderDate) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderDate = orderDate;
    }

    public Order() {

    }

    //getter
    public int getOrderId() {
        return orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    //setter


    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
