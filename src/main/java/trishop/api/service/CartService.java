package trishop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trishop.api.configuration.JwtRequestFilter;
import trishop.api.dao.CartDao;
import trishop.api.dao.ProductDao;
import trishop.api.dao.UserDao;
import trishop.api.entity.Cart;
import trishop.api.entity.Product;
import trishop.api.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;
    public Cart addToCart(Integer productId) {
        Product product = productDao.findById(productId).get();
        String currentUserName = JwtRequestFilter.CURRENT_USER;
        User user = null;

        if(currentUserName != null) {
            user = userDao.findById(currentUserName).get();
        }

        List<Cart> existingCartList = cartDao.findByUser(user);
        List<Cart> filteredList = existingCartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());
        if(filteredList.size() > 0) {
            return null;
        }
        if(product != null && user != null) {
            Cart cart = new Cart(product, user);
            return cartDao.save(cart);
        }
        return null;
    }

    public List<Cart> getCartDetails() {
        String username = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(username).get();
        return cartDao.findByUser(user);
    }

    public void deleteCartItem(Integer cartId) {
        cartDao.deleteById(cartId);
    }
}
