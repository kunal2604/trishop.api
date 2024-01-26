package trishop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import trishop.api.entity.Product;
import trishop.api.service.ProductService;

@RestController
public class ProductController {
    @Autowired
    private ProductService _productService;
    @PostMapping({"/product/add"})
    public Product addNewProduct(@RequestBody Product newProduct) {
        return _productService.addNewProduct(newProduct);
    }
}
