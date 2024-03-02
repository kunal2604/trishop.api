package trishop.api.entity;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private String orderName;
    private String orderAddress;

    private String orderContact;
    private String OrderAlternateContact;
    private String orderStatus;
    private Double orderAmount;
    @OneToOne
    private Product product;
    @OneToOne
    private User buyer;

    public OrderDetail() {}
    public OrderDetail(String orderName, String orderAddress, String orderContact, String orderAlternateContact, String orderStatus, Double orderAmount, Product product, User buyer) {
        this.orderName = orderName;
        this.orderAddress = orderAddress;
        this.orderContact = orderContact;
        OrderAlternateContact = orderAlternateContact;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.product = product;
        this.buyer = buyer;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String OrderName) {
        this.orderName = OrderName;
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
        return OrderAlternateContact;
    }

    public void setOrderAlternateContact(String OrderAlternateContact) {
        this.OrderAlternateContact = OrderAlternateContact;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }
}
