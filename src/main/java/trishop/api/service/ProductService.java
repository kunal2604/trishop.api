package trishop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trishop.api.dao.ProductDao;
import trishop.api.entity.Product;

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
}
