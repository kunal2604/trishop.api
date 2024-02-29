package trishop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import trishop.api.configuration.JwtRequestFilter;
import trishop.api.dao.CartDao;
import trishop.api.dao.ProductDao;
import trishop.api.dao.UserDao;
import trishop.api.entity.Cart;
import trishop.api.entity.Product;
import trishop.api.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductDao _productDao;
    @Autowired
    private UserDao _userDao;
    @Autowired
    private CartDao _cartDao;

    public Product addNewProduct(Product newProduct) {
        return _productDao.save(newProduct);
    }

    public List<Product> getAllProducts(int pageNumber, int pageSize, String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        if(searchKey.isEmpty()) {
            return (List<Product>) _productDao.findAll(pageable);
        }
        else {
            return (List<Product>) _productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
                   searchKey, searchKey, pageable);
        }

    }

    public Product getProductDetailsById(int productId)
    {
        return _productDao.findById(productId).get();
    }

    public void deleteProductDetails(int productId) {
        _productDao.deleteById(productId);
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
        if(isSingleProductCheckout && productId != 0) {
            // Buy single product
            List<Product> list =  new ArrayList<>();
            Product product = _productDao.findById(productId).get();
            list.add(product);
            return list;
        }
        else {
            // Buy all products in cart
            String username = JwtRequestFilter.CURRENT_USER;
            User user = _userDao.findById(username).get();
            List<Cart> carts = _cartDao.findByUser(user);
            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
        }
    }
}
