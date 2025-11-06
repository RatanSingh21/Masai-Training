package com.example.PaymentService.Service;


import com.example.PaymentService.Entity.Payment;
import com.example.PaymentService.Repo.PaymentRepo;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepo repo;


    @Value("${server.port}")
    private String serverPort;

    public ResponseEntity<Payment> placePayment(Payment payment)
    {
        payment.setServed("Instance running on port " + serverPort);
        return new ResponseEntity<>(repo.save(payment), HttpStatus.OK);

    }

    public ResponseEntity<Payment> getById(Long id) {

        Payment payment = repo.findById(id).orElseThrow(()-> new RuntimeException("Not Found"));
        payment.setServed(serverPort);
        System.out.println(serverPort);

        return new ResponseEntity<>(payment,HttpStatus.OK);
    }
}
