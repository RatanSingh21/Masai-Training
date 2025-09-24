package in.ratansgh.order_management.service;

import in.ratansgh.order_management.Config.RestTemplateConfig;
import in.ratansgh.order_management.DTO.ProductDto;
import in.ratansgh.order_management.entities.Order;
import in.ratansgh.order_management.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OrderServiceImpl{

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    private final String product_Url = "http://localhost:8080//product/{id}";

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public ResponseEntity<Order> addDb (Long id, Integer quantity){
        ProductDto productDto = restTemplateConfig.restTemplate().getForObject(product_Url, ProductDto.class,id);

        if (productDto == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (productDto.getQuantity() < quantity){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Order order = new Order();
        order.setProductName(productDto.getName());
        order.setQuantity(quantity);

        orderRepo.save(order);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}