package trishop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trishop.api.configuration.JwtRequestFilter;
import trishop.api.dao.CartDao;
import trishop.api.dao.OrderDetailDao;
import trishop.api.dao.ProductDao;
import trishop.api.dao.UserDao;
import trishop.api.entity.*;

import java.util.List;

@Service
public class OrderDetailService {
    private static final String ORDER_PLACED = "placed";
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;
    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
        String currentUserName = JwtRequestFilter.CURRENT_USER;
        User currentUser = userDao.findById(currentUserName).get();

        for(OrderProductQuantity o : productQuantityList) {
            Product product = productDao.findById(o.getProductId()).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getOrderName(),
                    orderInput.getOrderAddress(),
                    orderInput.getOrderContact(),
                    orderInput.getOrderAlternateContact(),
                    ORDER_PLACED,
                    (product.getPrice() - product.getDiscount()) * o.getQuantity(),
                    product,
                    currentUser
            );

            orderDetailDao.save(orderDetail);

            // Empty the cart if product bought from cart
            if(!isSingleProductCheckout) {
                List<Cart> carts = cartDao.findByUser(currentUser);
                carts.stream().forEach(x -> cartDao.deleteById(x.getCartId()));
            }
        }
    }

    public List<OrderDetail> getAllOrders() {
        String currentUserName = JwtRequestFilter.CURRENT_USER;
        User currentUser = userDao.findById(currentUserName).get();
        return orderDetailDao.findByBuyer(currentUser);
    }
}
