package in.ratansgh.product_management.controller;

import in.ratansgh.product_management.entities.Product;
import in.ratansgh.product_management.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
