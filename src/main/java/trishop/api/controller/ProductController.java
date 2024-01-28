package trishop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import trishop.api.entity.ImageModel;
import trishop.api.entity.Product;
import trishop.api.service.ProductService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {
    @Autowired
    private ProductService _productService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = {"/product/add"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product newProduct,
                                 @RequestPart("imageFile")MultipartFile[] files) {
        try {
            Set<ImageModel> uploadedImages = uploadImage(files);
            newProduct.setProductImages(uploadedImages);
            return _productService.addNewProduct(newProduct);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<ImageModel> uploadImage(MultipartFile[] files) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for(MultipartFile file : files) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @GetMapping({"/getAllProducts"})
    public List<Product> getAllProducts() {
        return _productService.getAllProducts();
    }
}
