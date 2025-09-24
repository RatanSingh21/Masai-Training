package in.ratansgh.product_management.service;

import in.ratansgh.product_management.entities.Product;

import java.util.Optional;

public interface ProductServiceImpl {

    Product addProduct(Product product);
    Optional<Product> getById(Long id);

}

