package in.ratansgh.product_management.service;

import in.ratansgh.product_management.entities.Product;
import in.ratansgh.product_management.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements ProductServiceImpl{

    @Autowired
    private ProductRepo productRepo;

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Optional<Product> getById(Long id) {
        return productRepo.findById(id);
    }

}
