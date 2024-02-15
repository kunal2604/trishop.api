package trishop.api.entity;

import java.util.List;

public class OrderInput {
    private String orderName;
    private String orderAddress;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String OrderAddress) {
        this.orderAddress = OrderAddress;
    }

    public String getOrderContact() {
        return orderContact;
    }

    public void setOrderContact(String OrderContact) {
        this.orderContact = OrderContact;
    }

    public String getOrderAlternateContact() {
        return orderAlternateContact;
    }

    public void setOrderAlternateContact(String OrderAlternateContact) {
        this.orderAlternateContact = OrderAlternateContact;
    }

    public List<OrderProductQuantity> getOrderProductQuantityList() {
        return orderProductQuantityList;
    }

    public void setOrderProductQuantityList(List<OrderProductQuantity> orderProductQuantityList) {
        this.orderProductQuantityList = orderProductQuantityList;
    }

    private String orderContact;
    private String orderAlternateContact;
    private List<OrderProductQuantity> orderProductQuantityList;

}
