package com.example.OrderService.Service;

import com.example.OrderService.Config.RestTemplateConfig;
import com.example.OrderService.Dto.Payment;
import com.example.OrderService.Dto.ResponseDto;
import com.example.OrderService.Entity.Order;
import com.example.OrderService.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    OrderRepo repo;

    @Autowired
    RestTemplate restTemplateConfig;


    public ResponseEntity<Order> placeOrder(Order order)
    {
        return new ResponseEntity<>(repo.save(order), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> getById(Long id)
    {
        Order order = repo.findById(id).orElseThrow(()->new RuntimeException("Not FOund"));

        Payment payment = restTemplateConfig.getForObject("http://payment-service/payments/"+order.getOrderId() , Payment.class);
        assert payment != null;
        ResponseDto dto = new ResponseDto(order.getOrderId(), order.getProductName(), payment.getOrderStatus(), payment.getServed());
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
}
