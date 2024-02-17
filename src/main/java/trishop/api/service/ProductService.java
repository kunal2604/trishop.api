package trishop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trishop.api.dao.ProductDao;
import trishop.api.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao _productDao;
    public Product addNewProduct(Product newProduct) {
        return _productDao.save(newProduct);
    }

    public List<Product> getAllProducts() {
        return (List<Product>) _productDao.findAll();
    }

    public Product getProductDetailsById(int productId)
    {
        return _productDao.findById(productId).get();
    }

    public void deleteProductDetails(int productId) {
        _productDao.deleteById(productId);
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
        if(isSingleProductCheckout) {
            // Buy single product
            List<Product> list =  new ArrayList<>();
            Product product = _productDao.findById(productId).get();
            list.add(product);
            return list;
        }
        else {
            // Buy all products in cart
        }
        return new ArrayList<>();
    }
}
